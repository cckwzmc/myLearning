<?php
if(!defined('DEDEINC'))
{
	exit("Request Error!");
}

//����û����ĺϷ���
function CheckUserID($uid,$msgtitle='�û���',$ckhas=true)
{
	global $cfg_mb_notallow,$cfg_mb_idmin,$cfg_md_idurl,$cfg_soft_lang,$dsql;
	if($cfg_mb_notallow != '')
	{
		$nas = explode(',',$cfg_mb_notallow);
		if(in_array($uid,$nas))
		{
			return $msgtitle.'Ϊϵͳ��ֹ�ı�ʶ��';
		}
	}
	if($cfg_md_idurl=='Y' && eregi("[^a-z0-9]",$uid))
	{
		return $msgtitle.'������Ӣ����ĸ��������ɣ�';
	}

	if($cfg_soft_lang=='utf-8')
	{
		$ck_uid = utf82gb($uid);
	}
	else
	{
		$ck_uid = $uid;
	}

	for($i=0;isset($ck_uid[$i]);$i++)
	{
			if(ord($ck_uid[$i]) > 0x80)
			{
				if(isset($ck_uid[$i+1]) && ord($ck_uid[$i+1])>0x40)
				{
					$i++;
				}
				else
				{
					return $msgtitle.'���ܺ������룬���������Ӣ����ĸ��������ϣ�';
				}
			}
			else
			{
				if(eregi("[^0-9a-z@\.-]",$ck_uid[$i]))
				{
					return $msgtitle.'���ܺ��� [@]��[.]��[-]�����������ţ�';
				}
			}
	}
	if($ckhas)
	{
		$row = $dsql->GetOne("Select * From `#@__member` where userid like '$uid' ");
		if(is_array($row)) return $msgtitle."�Ѿ����ڣ�";
	}
	return 'ok';
}


//��վ��Ա��¼��
class MemberLogin
{
	var $M_ID;
	var $M_LoginID;
	var $M_MbType;
	var $M_Uprank;
	var $M_Money;
	var $M_Scores;
	var $M_UserName;
	var $M_Rank;
	var $M_LoginTime;
	var $M_KeepTime;
	var $M_Upmoney;
	var $M_Spacesta;
	var $fields;
	var $isAdmin;

	var $M_Honor = '';

	//php5���캯��
	function __construct($kptime = -1)
	{
		global $dsql;
		if($kptime==-1)
		{
			$this->M_KeepTime = 3600 * 24 * 7;
		}
		else
		{
			$this->M_KeepTime = $kptime;
		}
		$this->M_ID = $this->GetNum(GetCookie("DedeUserID"));
		$this->M_LoginTime = GetCookie("DedeLoginTime");
		$this->fields = array();
		$this->isAdmin = false;
		if(empty($this->M_ID))
		{
			$this->ResetUser();
		}
		else
		{
			$this->M_ID = intval($this->M_ID);
			$this->fields = $dsql->GetOne("Select * From `#@__member` where mid='{$this->M_ID}' ");
			if(is_array($this->fields))
			{
				//���һСʱ����һ���û���¼ʱ��
				if(time() - $this->M_LoginTime > 3600)
				{
					$dsql->ExecuteNoneQuery("update `#@__member` set logintime='".time()."',loginip='".GetIP()."' where mid='".$this->fields['mid']."';");
					PutCookie("DedeLoginTime",time(),$this->M_KeepTime);
				}
				$this->M_LoginID = $this->fields['userid'];
				$this->M_MbType = $this->fields['mtype'];
				$this->M_Uprank = $this->fields['uprank'];
				$this->M_Money = $this->fields['money'];
				$this->M_UserName = $this->fields['uname'];
				$this->M_Upmoney = $this->fields['upmoney'];
				$this->M_Scores = $this->fields['scores'];
				$this->M_Rank = $this->fields['rank'];
				$this->M_Spacesta = $this->fields['spacesta'];
				$sql = "Select titles From #@__scores where integral<={$this->fields['scores']} order by integral desc";
				$scrow = $dsql->GetOne($sql);
				$this->fields['honor'] = $scrow['titles'];
				$this->M_Honor = $this->fields['honor'];
				if($this->fields['matt']==10)
				{
					$this->isAdmin = true;
				}
			}
			else
			{
				$this->ResetUser();
			}
		}
	}

	function MemberLogin($kptime = -1)
	{
		$this->__construct($kptime);
	}

	//�˳�cookie�ĻỰ
	function ExitCookie()
	{
		$this->ResetUser();
	}

	//��֤�û��Ƿ��Ѿ���¼
	function IsLogin()
	{
		if($this->M_ID > 0) return true;
		else return false;
	}

	//����û��ϴ��ռ�
	function GetUserSpace()
	{
		global $dsql;
		$uid = $this->M_ID;
		$row = $dsql->GetOne("select sum(filesize) as fs From `#@__uploads` where mid='$uid'; ");
		return $row['fs'];
	}

	function CheckUserSpace()
	{
		global $cfg_mb_max;
		$uid = $this->M_ID;
		$hasuse = $this->GetUserSpace();
		$maxSize = $cfg_mb_max * 1024 * 1024;
		if($hasuse >= $maxSize)
		{
			ShowMsg('��Ŀռ��������������ϴ����ļ���','-1');
			exit();
		}
	}

	//�����û���Ϣͳ�Ʊ�
	function UpdateUserTj($field,$uptype='add')
	{
		global $dsql;
		$mid = $this->M_ID;
		$arr = $dsql->GetOne("Select * `#@__member_tj` where mid='$mid' ");
		if(!is_array($arr))
		{
			$arr = array('article'=>0,'album'=>0,'archives'=>0,'homecount'=>0,'pagecount'=>0,'feedback'=>0,'friend'=>0,'stow'=>0);
		}
		extract($arr);
		if(isset($$field))
		{
			if($uptype=='add')
			{
				$$field++;
			}
			else if($$field > 0)
			{
				$$field--;
			}
		}
		$inquery = "INSERT INTO `#@__member_tj` (`mid`,`article`,`album`,`archives`,`homecount`,`pagecount`,`feedback`,`friend`,`stow`)
               VALUES ('$mid','$article','$album','$archives','$homecount','$pagecount','$feedback','$friend','$stow'); ";
		$dsql->ExecuteNoneQuery("Delete From `#@__member_tj` where mid='$mid' ");
		$dsql->ExecuteNoneQuery($inquery);
	}

	//�����û���Ϣ
	function ResetUser()
	{
		$this->fields = '';
		$this->M_ID = 0;
		$this->M_LoginID = '';
		$this->M_Rank = 0;
		$this->M_Uprank = 0;
		$this->M_Money = 0;
		$this->M_UserName = "";
		$this->M_LoginTime = 0;
		$this->M_MbType = '';
		$this->M_Scores = 0;
		$this->M_Spacesta = -2;
		DropCookie('DedeUserID');
		DropCookie('DedeLoginTime');
	}

	//��ȡ����ֵ
	function GetNum($fnum){
		$fnum = ereg_replace("[^0-9\.]",'',$fnum);
		return $fnum;
	}

	//�û���¼
	//�ѵ�¼����תΪָ������md5����
	function GetEncodePwd($pwd)
	{
		global $cfg_mb_pwdtype;
		if(empty($cfg_mb_pwdtype)) $cfg_mb_pwdtype = '32';
		switch($cfg_mb_pwdtype)
		{
			case 'l16':
				return substr(md5($pwd), 0, 16);
			case 'r16':
				return substr(md5($pwd), 16, 16);
			case 'm16':
				return substr(md5($pwd), 8, 16);
			default:
				return md5($pwd);
		}
	}
	
	//�����ݿ�����תΪ�ض�����
	//������ݿ����������ĵģ�������֧��
	function GetShortPwd($dbpwd)
	{
		global $cfg_mb_pwdtype;
		if(empty($cfg_mb_pwdtype)) $cfg_mb_pwdtype = '32';
		$dbpwd = trim($dbpwd);
		if(strlen($dbpwd)==16)
		{
			return $dbpwd;
		}
		else
		{
			switch($cfg_mb_pwdtype)
			{
				case 'l16':
					return substr($dbpwd, 0, 16);
				case 'r16':
					return substr($dbpwd, 16, 16);
				case 'm16':
					return substr($dbpwd, 8, 16);
				default:
					return $dbpwd;
			}
		}
	}
	
	function CheckUser(&$loginuser,$loginpwd)
	{
		global $dsql;

		//����û����ĺϷ���
		$rs = CheckUserID($loginuser,'�û���',false);

		//�û�������ȷʱ������֤����ԭ��¼��ͨ�����÷��ش�����ʾ��Ϣ
		if($rs!='ok')
		{
			$loginuser = $rs;
			return '0';
		}

		//matt=10 �ǹ���Ա������ǰ̨�ʺţ�Ϊ�˰�ȫ���������ʺ�ֻ�ܴӺ�̨��¼������ֱ�Ӵ�ǰ̨��¼
		$row = $dsql->GetOne("Select mid,matt,pwd From `#@__member` where userid like '$loginuser' ");
		if(is_array($row))
		{
			if($this->GetShortPwd($row['pwd']) != $this->GetEncodePwd($loginpwd))
			{
				return -1;
			}
			else
			{
				//����Ա�ʺŲ������ǰ̨��¼
				if($row['matt']==10) {
					return -2;
				}
				else {
					$this->PutLoginInfo($row['mid']);
					return 1;
				}
			}
		}
		else
		{
			return 0;
		}
	}

	//�����û�cookie
	function PutLoginInfo($uid)
	{
		$this->M_ID = $uid;
		$this->M_LoginTime = time();
		if($this->M_KeepTime > 0)
		{
			PutCookie('DedeUserID',$uid,$this->M_KeepTime);
			PutCookie('DedeLoginTime',$this->M_LoginTime,$this->M_KeepTime);
		}
		else
		{
			PutCookie('DedeUserID',$uid);
			PutCookie('DedeLoginTime',$this->M_LoginTime);
		}
	}

	//��û�ԱĿǰ��״̬
	function GetSta($dsql)
	{
		$sta = '';
		if($this->M_Rank==0)
		{
			$sta .= "��Ŀǰ������ǣ���ͨ��Ա";
		}
		else
		{
			$row = $dsql->GetOne("Select membername From `#@__arcrank` where rank='".$this->M_Rank."'");
			$sta .= "��Ŀǰ������ǣ�".$row['membername'];
		}
		if($this->M_Uprank>0)
		{
			$row = $dsql->GetOne("Select membername From `#@__arcrank` where rank='".$this->M_Uprank."'");
			$mname = $row['membername'];
			$sta .= " ������������Ϊ��$mname ";
		}
		$sta .= " ӵ�н�ң�{$this->M_Money} �������֣�{$this->M_Scores} ��";
		if($this->M_Upmoney>0)
		{
			$sta .= "���������� ".$this->M_Upmoney." �����";
		}
		$sta .= "��";
		return $sta;
	}
}
?>