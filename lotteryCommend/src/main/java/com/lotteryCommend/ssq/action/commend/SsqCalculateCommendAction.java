package com.lotteryCommend.ssq.action.commend;

import com.lotteryCommend.ssq.model.commend.SsqCalculateModel;
import com.lotteryCommend.web.core.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class SsqCalculateCommendAction extends BaseAction implements ModelDriven<SsqCalculateModel>,Preparable{

	private static final long serialVersionUID = -8972084249512970073L;

	@Override
	public SsqCalculateModel getModel() {
		return null;
	}
	@Override
	public void prepare() throws Exception {
		
	}
	public String calculateCommend(){
		
		return SUCCESS;
	}
}
