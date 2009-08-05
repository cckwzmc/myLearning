<?php
if(!defined('DEDEINC'))
{
	exit("Request Error!");
}
require_once(DEDEINC."/typelink.class.php");
require_once(DEDEINC."/channelunit.class.php");
require_once(DEDEINC."/downmix.inc.php");

@set_time_limit(0);
class Archives
{
	var $TypeLink;
	var $ChannelUnit;
	var $dsql;
	var $Fields;
	var $dtp;
	var $ArcID;
	var $SplitPageField;
	var $SplitFields;
	var $NowPage;
	var $TotalPage;
	var $NameFirst;
	var $ShortName;
	var $FixedValues;
	var $TempSource;
	var $IsError;
	var $SplitTitles;
	var $PreNext;
	var $addTableRow;
	var $a_xstype="";
 
	//php5���캯��
	function __construct($aid)
	{
		global $dsql;
		$a_aid='';
		
		if(is_array($aid)){
			$a_aid=$aid[0];
			$this->a_xstype=$aid[1];
		}else{
			$a_aid=$aid;
		}
		$this->IsError = false;
		$this->ArcID = $a_aid;//$aid;
		$this->PreNext = array();

		$this->dsql = $dsql;
		$query = "Select channel,typeid from `#@__arctiny` where id='$a_aid' ";
		$arr = $this->dsql->GetOne($query);
		if(!is_array($arr))
		{
			$this->IsError = true;
		}
		else
		{
			if($arr['channel']==0)
			{
				$arr['channel']=1;
			}
			$this->ChannelUnit = new ChannelUnit($arr['channel'],$a_aid);
			$this->TypeLink = new TypeLink($arr['typeid']);
			if($this->ChannelUnit->ChannelInfos['issystem']!=-1)
			{
				
				$query = "Select arc.*,tp.reid,tp.typedir,ch.addtable 
				from `#@__archives` arc
 		        		left join #@__arctype tp on tp.id=arc.typeid
 		         		left join #@__channeltype as ch on arc.channel = ch.id
 		         		where arc.id='$a_aid' ";
 		    if('page'==$this->a_xstype){
 		    	$query.=" And arc.flag like'%t%' ";	
 		    }else{
 		    	$query.=" And arc.flag like'%x%' and isbookpage is not null ";	
 		    }     		
				$this->Fields = $this->dsql->GetOne($query);
			}
			else
			{
				$this->Fields['title'] = '';
				$this->Fields['money'] = $this->Fields['arcrank'] = 0;
				$this->Fields['senddate'] = $this->Fields['pubdate'] = $this->Fields['mid'] = $this->Fields['adminid'] = 0;
				$this->Fields['ismake'] = 1;
				$this->Fields['filename'] = '';
			}
			if('page'==$this->a_xstype){
				$this->Fields['a_xstype'] = 'page';
			}
			if($this->TypeLink->TypeInfos['corank'] > 0 && $this->Fields['arcrank']==0)
			{
				$this->Fields['arcrank'] = $this->TypeLink->TypeInfos['corank'];
			}

			$this->Fields['tags'] = GetTags($a_aid);
			$this->dtp = new DedeTagParse();
			$this->dtp->refObj = $this;
			$this->SplitPageField = $this->ChannelUnit->SplitPageField;
			$this->SplitFields = '';
			$this->TotalPage = 1;
			$this->NameFirst = '';
			$this->ShortName = 'html';
			$this->FixedValues = '';
			$this->TempSource = '';
			if(empty($GLOBALS["pageno"]))
			{
				$this->NowPage = 1;
			}
			else
			{
				$this->NowPage = $GLOBALS["pageno"];
			}

			//������ֶ����ݴ���
			$this->Fields['aid'] = $a_aid;
			$this->Fields['id'] = $a_aid;
			$this->Fields['position'] = $this->TypeLink->GetPositionLink(true);
			$this->Fields['typeid'] = $arr['typeid'];

			//����һЩȫ�ֲ�����ֵ
			foreach($GLOBALS['PubFields'] as $k=>$v)
			{
				$this->Fields[$k] = $v;
			}

			//Ϊ�˼����ظ���ѯ������ֱ�ӰѸ��ӱ��ѯ��¼���� $this->addTableRow �У��� ParAddTable() ���ٲ�ѯ
			if($this->ChannelUnit->ChannelInfos['addtable']!='')
			{
				$query = "SELECT * FROM `{$this->ChannelUnit->ChannelInfos['addtable']}`WHERE `aid` = '$a_aid'";
				$this->addTableRow = $this->dsql->GetOne($query);
			}

			//issystem==-1 ��ʾ����ģ�ͣ�����ģ�Ͳ�֧��redirecturl�������������޶�������ͨģ�ͲŽ��������ѯ
			if($this->ChannelUnit->ChannelInfos['addtable']!='' && $this->ChannelUnit->ChannelInfos['issystem']!=-1)
			{
				if(is_array($this->addTableRow))
				{
					$this->Fields['redirecturl'] = $this->addTableRow['redirecturl'];
					$this->Fields['templet'] = $this->addTableRow['templet'];
					$this->Fields['userip'] = $this->addTableRow['userip'];
				}
				$this->Fields['templet'] = (empty($this->Fields['templet']) ? '' : trim($this->Fields['templet']));
				$this->Fields['redirecturl'] = (empty($this->Fields['redirecturl']) ? '' : trim($this->Fields['redirecturl']));
				$this->Fields['userip'] = (empty($this->Fields['userip']) ? '' : trim($this->Fields['userip']));
			}
			else
			{
				$this->Fields['templet'] = $this->Fields['redirecturl'] = '';
			}
		}//!error
	}

	//php4���캯��
	function Archives($aid)
	{
		$this->__construct($aid);
	}

	//�������ӱ������
	function ParAddTable()
	{
		//��ȡ���ӱ���Ϣ�����Ѹ��ӱ�����Ͼ������봦����뵽$this->Fields�У��Է�����ģ������ {dede:field name='fieldname' /} ���ͳһ����
		if($this->ChannelUnit->ChannelInfos['addtable']!='')
		{
			$row = $this->addTableRow;
			if($this->ChannelUnit->ChannelInfos['issystem']==-1)
			{
				$this->Fields['title'] = $row['title'];
				$this->Fields['senddate'] = $this->Fields['pubdate'] = $row['senddate'];
				$this->Fields['mid'] = $this->Fields['adminid'] = $row['mid'];
				$this->Fields['ismake'] = 1;
				$this->Fields['arcrank'] = 0;
				$this->Fields['money']=0;
				$this->Fields['filename'] = '';
			}

			if(is_array($row))
			{
				foreach($row as $k=>$v) $row[strtolower($k)] = $v;
			}
			if(is_array($this->ChannelUnit->ChannelFields) && !empty($this->ChannelUnit->ChannelFields))
			{
				foreach($this->ChannelUnit->ChannelFields as $k=>$arr)
				{
					if(isset($row[$k]))
					{
						if(!empty($arr['rename']))
						{
							$nk = $arr['rename'];
						}
						else
						{
							$nk = $k;
						}
						$cobj = $this->GetCurTag($k);
						if(is_object($cobj))
						{
							$this->Fields[$nk] = $this->ChannelUnit->MakeField($k,$row[$k],$this->GetCurTag($k));
						}
						else
						{
							$this->Fields[$nk] = $row[$k];
						}
						if($arr['type']=='htmltext' && $GLOBALS['cfg_keyword_replace']=='Y' && !empty($this->Fields['keywords']))
						{
							$this->Fields[$nk] = $this->ReplaceKeyword($this->Fields['keywords'],$this->Fields[$nk]);
						}
					}
				}//End foreach
			}
			//����ȫ�ֻ�������
			$this->Fields['typename'] = $this->TypeLink->TypeInfos['typename'];
			SetSysEnv($this->Fields['typeid'],$this->Fields['typename'],$this->Fields['aid'],$this->Fields['title'],'archives');
		}
		//��ɸ��ӱ���Ϣ��ȡ
		unset($row);

		//����Ҫ��ҳ��ʾ���ֶ�
		$this->SplitTitles = Array();
		if($this->SplitPageField!='' && $GLOBALS['cfg_arcsptitle']='Y'
		&& isset($this->Fields[$this->SplitPageField]))
		{
			$this->SplitFields = explode("#p#",$this->Fields[$this->SplitPageField]);
			$i = 1;
			foreach($this->SplitFields as $k=>$v)
			{
				$tmpv = cn_substr($v,50);
				$pos = strpos($tmpv,'#e#');
				if($pos>0)
				{
					$st = trim(cn_substr($tmpv,$pos));
					if($st==""||$st=="������"||$st=="��ҳ����")
					{
						$this->SplitFields[$k] = preg_replace("/^(.*)#e#/is","",$v);
						continue;
					}
					else
					{
						$this->SplitFields[$k] = preg_replace("/^(.*)#e#/is","",$v);
						$this->SplitTitles[$k] = $st;
					}
				}
				else
				{
					continue;
				}
				$i++;
			}
			$this->TotalPage = count($this->SplitFields);
			$this->Fields['totalpage'] = $this->TotalPage;
		}
		
		//����Ĭ������ͼ��
		if($this->Fields['litpic'] == '-' || $this->Fields['litpic'] == '')
		{
				$this->Fields['litpic'] = $GLOBALS['cfg_cmspath'].'/images/defaultpic.gif';
		}
		if(!eregi("^http://",$this->Fields['litpic']) && $GLOBALS['cfg_multi_site'] == 'Y')
		{
				$this->Fields['litpic'] = $GLOBALS['cfg_mainsite'].$this->Fields['litpic'];
		}
		$this->Fields['picname'] = $this->Fields['litpic'];
		
		//ģ����ֱ��ʹ��{dede:field name='image'/}��ȡ����ͼ
		$this->Fields['image'] = (!eregi('jpg|gif|png', $this->Fields['picname']) ? '' : "<img src='{$this->Fields['picname']}' />");
		
		//digg
		if($this->Fields['goodpost'] + $this->Fields['badpost'] == 0)
		{
				$this->Fields['goodper'] = $this->Fields['badper'] = 0;
		}
		else
		{
				$this->Fields['goodper'] = number_format($this->Fields['goodpost']/($this->Fields['goodpost']+$this->Fields['badpost']), 3)*100;
				$this->Fields['badper'] = 100 - $this->Fields['goodper'];
		}
		
	}

	//��õ�ǰ�ֶβ���
	function GetCurTag($fieldname)
	{
		if(!isset($this->dtp->CTags))
		{
			return '';
		}
		foreach($this->dtp->CTags as $ctag)
		{
			if($ctag->GetTagName()=='field' && $ctag->GetAtt('name')==$fieldname)
			{
				return $ctag;
			}
			else
			{
				continue;
			}
		}
		return '';
	}

	//���ɾ�̬HTML
	function MakeHtml()
	{
		if($this->IsError)
		{
			return '';
		}
		$this->Fields["displaytype"] = "st";
		//Ԥ����$th
		$this->LoadTemplet();
		$this->ParAddTable();
		$this->ParseTempletsFirst();

		//����Ҫ�������ļ�����
		$filename = GetFileNewName(
		Array($this->ArcID,$this->a_xstype,(empty($this->Fields['isbookpage'])?$this->Fields['id']:$this->Fields['isbookpage'])),$this->Fields['typeid'],$this->Fields['senddate'],
		$this->Fields['title'],$this->Fields['ismake'],$this->Fields['arcrank'],
		$this->TypeLink->TypeInfos['namerule'],$this->TypeLink->TypeInfos['typedir'],$this->Fields['money'],$this->Fields['filename']
		);

		$filenames  = explode(".",$filename);
		$this->ShortName = $filenames[count($filenames)-1];
		if($this->ShortName=="")
		{
			$this->ShortName = "html";
		}
		$fileFirst = eregi_replace("\.".$this->ShortName."$","",$filename);
		$this->Fields['namehand'] = basename($fileFirst);
		$filenames  = explode("/",$filename);
		$this->NameFirst = eregi_replace("\.".$this->ShortName."$","",$filenames[count($filenames)-1]);
		if($this->NameFirst=="")
		{
			$this->NameFirst = $this->arcID;
		}

		//��õ�ǰ�ĵ���ȫ��
		$filenameFull = GetFileUrl(
		$this->ArcID,$this->Fields["typeid"],$this->Fields["senddate"],
		$this->Fields["title"],$this->Fields["ismake"],
		$this->Fields["arcrank"],$this->TypeLink->TypeInfos['namerule'],$this->TypeLink->TypeInfos['typedir'],$this->Fields["money"],$this->Fields['filename'],
		$this->TypeLink->TypeInfos['moresite'],$this->TypeLink->TypeInfos['siteurl'],$this->TypeLink->TypeInfos['sitepath']
		);
		$this->Fields['arcurl'] = $this->Fields['fullname'] = $filenameFull;

		//���������ò�����HTML������ֱ�ӷ�����ַ
		if($this->Fields['ismake']==-1||$this->Fields['arcrank']!=0||
		$this->Fields['typeid']==0||$this->Fields['money']>0)
		{
			return $this->GetTrueUrl($filename);
		}

		//��ת��ַ
		if($this->Fields['redirecturl']!='')
		{
			$truefilename = $this->GetTruePath().$fileFirst.".".$this->ShortName;
			$pageHtml = "<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=".$GLOBALS['cfg_soft_lang']."\">\n<title>ת��".$this->Fields['title']."</title>\n";
			$pageHtml .= "<meta http-equiv=\"refresh\" content=\"1;URL=".$this->Fields['redirecturl']."\">\n</head>\n<body>\n";
			$pageHtml .= "��������ת��".$this->Fields['title']."�����Ժ�...<br/><br/>\nת�����ݼ��:".$this->Fields['description']."\n</body>\n</html>\n";
			$fp = @fopen($truefilename,"w") or die("Create File False��$filename");
			fwrite($fp,$pageHtml);
			fclose($fp);
		}

		//ѭ������HTML�ļ�
		else
		{
			for($i=1;$i<=$this->TotalPage;$i++)
			{
				if($i>1)
				{
					$truefilename = $this->GetTruePath().$fileFirst."_".$i.".".$this->ShortName;
				}
				else
				{
					$truefilename = $this->GetTruePath().$filename;
				}
				$this->ParseDMFields($i,1);
				$this->dtp->SaveTo($truefilename);
			}
		}
		$this->dsql->ExecuteNoneQuery("Update `#@__archives` set ismake=1 where id='".$this->ArcID."'");
		return $this->GetTrueUrl($filename);
	}

	//�����ʵ����·��
	function GetTrueUrl($nurl)
	{
		return GetFileUrl
		(
				$this->Fields['id'],
				$this->Fields['typeid'],
				$this->Fields['senddate'],
				$this->Fields['title'],
				$this->Fields['ismake'],
				$this->Fields['arcrank'],
				$this->TypeLink->TypeInfos['namerule'],
				$this->TypeLink->TypeInfos['typedir'],
				$this->Fields['money'],
				$this->Fields['filename'],
				$this->TypeLink->TypeInfos['moresite'],
				$this->TypeLink->TypeInfos['siteurl'],
				$this->TypeLink->TypeInfos['sitepath']
		);
	}

	//���վ�����ʵ��·��
	function GetTruePath()
	{
		$truepath = $GLOBALS["cfg_basedir"];
		return $truepath;
	}

	//���ָ����ֵ���ֶ�
	function GetField($fname)
	{
		if(isset($this->Fields[$fname]))
		{
			return $this->Fields[$fname];
		}
		else
		{
			return '';
		}
	}

	//���ģ���ļ�λ��
	function GetTempletFile()
	{
		global $cfg_basedir,$cfg_templets_dir,$cfg_df_style;
		$cid = $this->ChannelUnit->ChannelInfos["nid"];
		if(!empty($this->Fields['templet']))
		{
			$filetag = MfTemplet($this->Fields['templet']);
		}
		else
		{
			if(!empty ($this->Fields['a_xstype'])&&"page"==$this->Fields['a_xstype']){
				$filetag = MfTemplet($this->TypeLink->TypeInfos["tempindex"]);
			}else{
				$filetag = MfTemplet($this->TypeLink->TypeInfos["temparticle"]);
			}
		}
		$tid = $this->Fields["typeid"];
		$filetag = str_replace("{cid}",$cid,$filetag);
		$filetag = str_replace("{tid}",$tid,$filetag);
		$tmpfile = $cfg_basedir.$cfg_templets_dir."/".$filetag;
		if($cid=='spec')
		{
			if($this->Fields['templet']!='')
			{
				$tmpfile = $cfg_basedir.$cfg_templets_dir."/".MfTemplet($this->Fields['templet']);
			}
			else
			{
				$tmpfile = $cfg_basedir.$cfg_templets_dir."/{$cfg_df_style}/article_spec.htm";
			}
		}
		if(!file_exists($tmpfile))
		{
			$tmpfile = $cfg_basedir.$cfg_templets_dir."/{$cfg_df_style}/article_default.htm";
		}
		return $tmpfile;
	}

	//��̬������
	function display()
	{
		if($this->IsError)
		{
			return '';
		}
		$this->Fields["displaytype"] = "dm";
		//Ԥ����
		$this->LoadTemplet();
		$this->ParAddTable();

		$this->ParseTempletsFirst();

		//��ת��ַ
		if($this->Fields['redirecturl']!="")
		{
			global $fileFirst;
			$truefilename = $this->GetTruePath().$fileFirst.".".$this->ShortName;
			$pageHtml = "<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=".$GLOBALS['cfg_soft_lang']."\">\n<title>ת��".$this->Fields['title']."</title>\n";
			$pageHtml .= "<meta http-equiv=\"refresh\" content=\"1;URL=".$this->Fields['redirecturl']."\">\n</head>\n<body>\n";
			$pageHtml .= "��������ת��".$this->Fields['title']."�����Ժ�...<br/><br/>\nת�����ݼ��:\n".$this->Fields['description']."\n</body>\n</html>\n";
			echo $pageHtml;
			exit();
		}
		$pageCount = $this->NowPage;
		$this->ParseDMFields($pageCount,0);
		$this->dtp->display();
	}

	//����ģ��
	function LoadTemplet()
	{
		if($this->TempSource=='')
		{
			$tempfile = $this->GetTempletFile();
			if(!file_exists($tempfile) || !is_file($tempfile))
			{
				echo "ģ���ļ������ڣ��޷������ĵ���";
				exit();
			}
			$this->dtp->LoadTemplate($tempfile);
			$this->TempSource = $this->dtp->SourceString;
		}
		else
		{
			$this->dtp->LoadSource($this->TempSource);
		}
	}

	//����ģ�壬�Թ̶��ı�ǽ��г�ʼ��ֵ
	function ParseTempletsFirst()
	{
		if(empty($this->Fields['keywords']))
		{
			$this->Fields['keywords'] = '';
		}

		if(empty($this->Fields['reid']))
		{
			$this->Fields['reid'] = 0;
		}

		$GLOBALS['envs']['tags'] = $this->Fields['tags'];

		if(isset($this->TypeLink->TypeInfos['reid']))
		{
			$GLOBALS['envs']['reid'] = $this->TypeLink->TypeInfos['reid'];
		}

		$GLOBALS['envs']['keyword'] = $this->Fields['keywords'];

		$GLOBALS['envs']['typeid'] = $this->Fields['typeid'];

		$GLOBALS['envs']['topid'] = GetTopid($this->Fields['typeid']);

		$GLOBALS['envs']['aid'] = $GLOBALS['envs']['id'] = $this->Fields['id'];

		$GLOBALS['envs']['adminid'] = $GLOBALS['envs']['mid'] = $this->Fields['mid'];

		$GLOBALS['envs']['channelid'] = $this->TypeLink->TypeInfos['channeltype'];

		if($this->Fields['reid']>0)
		{
			$GLOBALS['envs']['typeid'] = $this->Fields['reid'];
		}

		MakeOneTag($this->dtp,$this);
	}

	//����ģ�壬��������ı䶯���и�ֵ
	function ParseDMFields($pageNo,$ismake=1)
	{
		$this->NowPage = $pageNo;
		$this->Fields['nowpage'] = $this->NowPage;
		if($this->SplitPageField!="" && isset($this->Fields[$this->SplitPageField]))
		{
			$this->Fields[$this->SplitPageField] = $this->SplitFields[$pageNo - 1];
		}

		//����ģ��
		if(is_array($this->dtp->CTags))
		{
			foreach($this->dtp->CTags as $i=>$ctag)
			{
				if($ctag->GetName()=='field')
				{
					$this->dtp->Assign($i,$this->GetField($ctag->GetAtt('name')));
				}
				else if($ctag->GetName()=='pagebreak')
				{
					if($ismake==0)
					{
						$this->dtp->Assign($i,$this->GetPagebreakDM($this->TotalPage,$this->NowPage,$this->ArcID));
					}
					else
					{
						$this->dtp->Assign($i,$this->GetPagebreak($this->TotalPage,$this->NowPage,$this->ArcID));
					}
				}
				else if($ctag->GetName()=='pagetitle')
				{
					if($ismake==0)
					{
						$this->dtp->Assign($i,$this->GetPageTitlesDM($ctag->GetAtt("style"),$pageNo));
					}
					else
					{
						$this->dtp->Assign($i,$this->GetPageTitlesST($ctag->GetAtt("style"),$pageNo));
					}
				}
				else if($ctag->GetName()=='prenext')
				{
					$this->dtp->Assign($i,$this->GetPreNext($ctag->GetAtt('get')));
				}
				else if($ctag->GetName()=='fieldlist')
				{
					$innertext = trim($ctag->GetInnerText());
					if($innertext=='') $innertext = GetSysTemplets('tag_fieldlist.htm');
					$dtp2 = new DedeTagParse();
					$dtp2->SetNameSpace('field','[',']');
					$dtp2->LoadSource($innertext);
					$oldSource = $dtp2->SourceString;
					$oldCtags = $dtp2->CTags;
					$res = '';
					if(is_array($this->ChannelUnit->ChannelFields) && is_array($dtp2->CTags))
					{
						foreach($this->ChannelUnit->ChannelFields as $k=>$v)
						{
							if(isset($v['autofield']) && empty($v['autofield'])) {
								continue;
							}
							$dtp2->SourceString = $oldSource;
							$dtp2->CTags = $oldCtags;
							$fname = $v['itemname'];
							foreach($dtp2->CTags as $tid=>$ctag2)
							{
								if($ctag2->GetName()=='name')
								{
									$dtp2->Assign($tid,$fname);
								}
								else if($ctag2->GetName()=='tagname')
								{
									$dtp2->Assign($tid,$k);
								}
								else if($ctag2->GetName()=='value')
								{
									$this->Fields[$k] = $this->ChannelUnit->MakeField($k,$this->Fields[$k],$ctag2);
									@$dtp2->Assign($tid,$this->Fields[$k]);
								}
							}
							$res .= $dtp2->GetResult();
						}
					}
					$this->dtp->Assign($i,$res);
				}//end case

			}//����ģ��ѭ��

		}
	}

	//�ر���ռ�õ���Դ
	function Close()
	{
		$this->FixedValues = '';
		$this->Fields = '';
	}

	//��ȡ��һƪ����һƪ����
	function GetPreNext($gtype='')
	{
		$rs = '';
		if(count($this->PreNext)<2)
		{
			$aid = $this->ArcID;
			$preR =  $this->dsql->GetOne("Select id From `#@__arctiny` where id<$aid And arcrank>-1 And typeid='{$this->Fields['typeid']}' order by id desc");
			$nextR = $this->dsql->GetOne("Select id From `#@__arctiny` where id>$aid And arcrank>-1 And typeid='{$this->Fields['typeid']}' order by id asc");
			$next = (is_array($nextR) ? " where arc.id={$nextR['id']} " : ' where 1>2 ');
			$pre = (is_array($preR) ? " where arc.id={$preR['id']} " : ' where 1>2 ');
			$query = "Select arc.id,arc.title,arc.shorttitle,arc.typeid,arc.ismake,arc.senddate,arc.arcrank,arc.money,arc.filename,
						t.typedir,t.typename,t.namerule,t.namerule2,t.ispart,arc.isbookpage,t.moresite,t.siteurl,t.sitepath
						from `#@__archives` arc left join #@__arctype t on arc.typeid=t.id   ";
			$nextRow = $this->dsql->GetOne($query.$next);
			$preRow = $this->dsql->GetOne($query.$pre);
			if(is_array($preRow))
			{
				$page_aid;
				$bookid="";
				if(empty($preRow['isbookpage'])){
					$bookid=$preRow['id'];
				}else{
					$bookid=$preRow['isbookpage'];
				}
				if(empty($preRow['isbookpage'])){
					$page_aid=array($preRow['id'],'table',$bookid);
				}else{
					$page_aid=array($preRow['id'],'detail',$bookid);
				}
				$mlink = GetFileUrl($page_aid,$preRow['typeid'],$preRow['senddate'],$preRow['title'],$preRow['ismake'],$preRow['arcrank'],
				$preRow['namerule'],$preRow['typedir'],$preRow['money'],$preRow['filename'],$preRow['moresite'],$preRow['siteurl'],$preRow['sitepath']);
				$this->PreNext['pre'] = "<a href='$mlink'>��һҳ</a> ";
			}
			else
			{
				$this->PreNext['pre'] = "��һҳ��û���� ";
			}
			$this->PreNext['pre'].="<a href='table.html'>����Ŀ¼</a>";
			$bookid=$preRow['isbookpage'];
			$this->PreNext['pre'].="<a href='/plus/recommend.php?aid=$bookid'>�Ƽ�����</a>";
			$this->PreNext['pre'].="<a href='/plus/stow.php?aid=$aid'>������ǩ</a>";
			if(is_array($nextRow))
			{
				$page_aid;
				$bookid="";
				if(empty($nextRow['isbookpage'])){
					$bookid=$nextRow['id'];
				}else{
					$bookid=$nextRow['isbookpage'];
				}
				if(empty($nextRow['isbookpage'])){
					$page_aid=array($nextRow['id'],'table',$bookid);
				}else{
					$page_aid=array($nextRow['id'],'detail',$bookid);
				}
				$mlink = GetFileUrl($page_aid,$nextRow['typeid'],$nextRow['senddate'],$nextRow['title'],$nextRow['ismake'],$nextRow['arcrank'],
				$nextRow['namerule'],$nextRow['typedir'],$nextRow['money'],$nextRow['filename'],$nextRow['moresite'],$nextRow['siteurl'],$nextRow['sitepath']);
				$this->PreNext['next'] = "<a href='$mlink'>��һҳ</a> ";
			}
			else
			{
				$this->PreNext['next'] = "��һҳ��û���� ";
			}
		}
		if($gtype=='pre')
		{
			$rs =  $this->PreNext['pre'];
		}
		else if($gtype=='next')
		{
			$rs =  $this->PreNext['next'];
		}
		else
		{
			$rs =  $this->PreNext['pre']." &nbsp; ".$this->PreNext['next'];
		}
		return $rs;
	}

	//��ö�̬ҳ���ҳ�б�
	function GetPagebreakDM($totalPage,$nowPage,$aid)
	{
		global $cfg_rewrite;
		if($totalPage==1)
		{
			return "";
		}
		$PageList = "<li><a>��".$totalPage."ҳ: </a></li>";
		$nPage = $nowPage-1;
		$lPage = $nowPage+1;
		if($nowPage==1)
		{
			$PageList.="<li><a href='#'>��һҳ</a></li>";
		}
		else
		{
			if($nPage==1)
			{
				$PageList.="<li><a href='view.php?aid=$aid'>��һҳ</a></li>";
				if($cfg_rewrite == 'Y')
				{
					$PageList = preg_replace("/.php\?aid=(\d+)/i",'-\\1-1.html',$PageList);
				}
			}
			else
			{
				$PageList.="<li><a href='view.php?aid=$aid&pageno=$nPage'>��һҳ</a></li>";
				if($cfg_rewrite == 'Y')
				{
					$PageList = str_replace(".php?aid=","-",$PageList);
					$PageList =  preg_replace("/&pageno=(\d+)/i",'-\\1.html',$PageList);
				}
			}
		}
		for($i=1;$i<=$totalPage;$i++)
		{
			if($i==1)
			{
				if($nowPage!=1)
				{
					$PageList.="<li><a href='view.php?aid=$aid'>1</a></li>";
					if($cfg_rewrite == 'Y')
					{
						$PageList = preg_replace("/.php\?aid=(\d+)/i",'-\\1-1.html',$PageList);
					}
				}
				else
				{
					$PageList.="<li class=\"thisclass\"><a>1</a></li>";
				}
			}
			else
			{
				$n = $i;
				if($nowPage!=$i)
				{
					$PageList.="<li><a href='view.php?aid=$aid&pageno=$i'>".$n."</a></li>";
					if($cfg_rewrite == 'Y')
					{
						$PageList = str_replace(".php?aid=","-",$PageList);
						$PageList =  preg_replace("/&pageno=(\d+)/i",'-\\1.html',$PageList);
					}
				}
				else
				{
					$PageList.="<li class=\"thisclass\"><a href='#'>{$n}</a></li>";
				}
			}
		}
		if($lPage <= $totalPage)
		{
			$PageList.="<li><a href='view.php?aid=$aid&pageno=$lPage'>��һҳ</a></li>";
			if($cfg_rewrite == 'Y')
			{
				$PageList = str_replace(".php?aid=","-",$PageList);
				$PageList =  preg_replace("/&pageno=(\d+)/i",'-\\1.html',$PageList);
			}
		}
		else
		{
			$PageList.= "<li><a href='#'>��һҳ</a></li>";
		}
		return $PageList;
	}

	//��þ�̬ҳ���ҳ�б�
	function GetPagebreak($totalPage,$nowPage,$aid)
	{
		if($totalPage==1)
		{
			return "";
		}
		$PageList = "<li><a>��".$totalPage."ҳ: </a></li>";
		$nPage = $nowPage-1;
		$lPage = $nowPage+1;
		if($nowPage==1)
		{
			$PageList.="<li><a href='#'>��һҳ</a></li>";
		}
		else
		{
			if($nPage==1)
			{
				$PageList.="<li><a href='".$this->NameFirst.".".$this->ShortName."'>��һҳ</a></li>";
			}
			else
			{
				$PageList.="<li><a href='".$this->NameFirst."_".$nPage.".".$this->ShortName."'>��һҳ</a></li>";
			}
		}
		for($i=1;$i<=$totalPage;$i++)
		{
			if($i==1)
			{
				if($nowPage!=1)
				{
					$PageList.="<li><a href='".$this->NameFirst.".".$this->ShortName."'>1</a></li>";
				}
				else
				{
					$PageList.="<li class=\"thisclass\"><a href='#'>1</a></li>";
				}
			}
			else
			{
				$n = $i;
				if($nowPage!=$i)
				{
					$PageList.="<li><a href='".$this->NameFirst."_".$i.".".$this->ShortName."'>".$n."</a></li>";
				}
				else
				{
					$PageList.="<li class=\"thisclass\"><a href='#'>{$n}</a></li>";
				}
			}
		}
		if($lPage <= $totalPage)
		{
			$PageList.="<li><a href='".$this->NameFirst."_".$lPage.".".$this->ShortName."'>��һҳ</a></li>";
		}
		else
		{
			$PageList.= "<li><a href='#'>��һҳ</a></li>";
		}
		return $PageList;
	}

	//��ö�̬ҳ��С����
	function GetPageTitlesDM($styleName,$pageNo)
	{
		if($this->TotalPage==1)
		{
			return "";
		}
		if(count($this->SplitTitles)==0)
		{
			return "";
		}
		$i=1;
		$aid = $this->ArcID;
		if($styleName=='link')
		{
			$revalue = "";
			foreach($this->SplitTitles as $k=>$v)
			{
				if($i==1)
				{
					$revalue .= "<a href='view.php?aid=$aid&pageno=$i'>$v</a> \r\n";
				}
				else
				{
					if($pageNo==$i)
					{
						$revalue .= " $v \r\n";
					}
					else
					{
						$revalue .= "<a href='view.php?aid=$aid&pageno=$i'>$v</a> \r\n";
					}
				}
				$i++;
			}
		}
		else
		{
			$revalue = "<select id='dedepagetitles' onchange='location.href=this.options[this.selectedIndex].value;'>\r\n";
			foreach($this->SplitTitles as $k=>$v)
			{
				if($i==1)
				{
					$revalue .= "<option value='".$this->Fields['phpurl']."/view.php?aid=$aid&pageno=$i'>{$i}��{$v}</option>\r\n";
				}
				else
				{
					if($pageNo==$i)
					{
						$revalue .= "<option value='".$this->Fields['phpurl']."/view.php?aid=$aid&pageno=$i' selected>{$i}��{$v}</option>\r\n";
					}
					else
					{
						$revalue .= "<option value='".$this->Fields['phpurl']."/view.php?aid=$aid&pageno=$i'>{$i}��{$v}</option>\r\n";
					}
				}
				$i++;
			}
			$revalue .= "</select>\r\n";
		}
		return $revalue;
	}

	//��þ�̬ҳ��С����
	function GetPageTitlesST($styleName,$pageNo)
	{
		if($this->TotalPage==1)
		{
			return "";
		}
		if(count($this->SplitTitles)==0)
		{
			return "";
		}
		$i=1;
		if($styleName=='link')
		{
			$revalue = "";
			foreach($this->SplitTitles as $k=>$v)
			{
				if($i==1)
				{
					$revalue .= "<a href='".$this->NameFirst.".".$this->ShortName."'>$v</a> \r\n";
				}
				else
				{
					if($pageNo==$i)
					{
						$revalue .= " $v \r\n";
					}
					else
					{
						$revalue .= "<a href='".$this->NameFirst."_".$i.".".$this->ShortName."'>$v</a> \r\n";
					}
				}
				$i++;
			}
		}
		else
		{
			$revalue = "<select id='dedepagetitles' onchange='location.href=this.options[this.selectedIndex].value;'>\r\n";
			foreach($this->SplitTitles as $k=>$v)
			{
				if($i==1)
				{
					$revalue .= "<option value='".$this->NameFirst.".".$this->ShortName."'>{$i}��{$v}</option>\r\n";
				}
				else
				{
					if($pageNo==$i)
					{
						$revalue .= "<option value='".$this->NameFirst."_".$i.".".$this->ShortName."' selected>{$i}��{$v}</option>\r\n";
					}
					else
					{
						$revalue .= "<option value='".$this->NameFirst."_".$i.".".$this->ShortName."'>{$i}��{$v}</option>\r\n";
					}
				}
				$i++;
			}
			$revalue .= "</select>\r\n";
		}
		return $revalue;
	}

	/**
	 * ������������, �ų�alt title <a></a>ֱ�ӵ��ַ��滻
	 *
	 * @param string $kw
	 * @param string $body
	 * @return string
	 */
	function ReplaceKeyword($kw,&$body)
	{
		global $cfg_cmspath;
		$maxkey = 5;
		$kws = explode(",",trim($kw));	//�Էֺ�Ϊ�����
		$i=0;
		$karr = $kaarr = $GLOBALS['replaced'] = array();

		//��ʱ���γ�����
		$body = preg_replace("/(<a(.*))(>)(.*)(<)(\/a>)/isU", '\\1-]-\\4-[-\\6', $body);

		foreach($kws as $k)
		{
			$k = trim($k);
			if($k!="")
			{
				if($i > $maxkey)
				{
					break;
				}
				$myrow = $this->dsql->GetOne("select * from #@__keywords where keyword='$k' And rpurl<>'' ");
				if(is_array($myrow))
				{
					$karr[] = $k;
					$GLOBALS['replaced'][$k] = 0;
					$kaarr[] = "<a href='{$myrow['rpurl']}'><u>$k</u></a>";
				}
				$i++;
			}
		}
		$body = preg_replace("/(^|>)([^<]+)(?=<|$)/sUe", "_highlight('\\2', \$karr, \$kaarr, '\\1')", $body);

		//�ָ�������
		$body = preg_replace("/(<a(.*))-\]-(.*)-\[-(\/a>)/isU", '\\1>\\3<\\4', $body);
		return $body;
	}


}//End Archives

//����ר��, �滻����ǿ��ܲ��ܴﵽ����
function _highlight($string, $words, $result, $pre)
{
	global $cfg_replace_num;
	$string = str_replace('\"', '"', $string);
	if($cfg_replace_num > 0)
	{
		foreach ($words as $key => $word)
		{
			if($GLOBALS['replaced'][$word] == 1)
			{
				continue;
			}
			$string = preg_replace("/".preg_quote($word)."/", $result[$key], $string, $cfg_replace_num);
			if(strpos($string, $word) !== false)
			{
				$GLOBALS['replaced'][$word] = 1;
			}
		}
	}
	else
	{
		$string = str_replace($words, $result, $string);
	}
	return $pre.$string;
}

?>