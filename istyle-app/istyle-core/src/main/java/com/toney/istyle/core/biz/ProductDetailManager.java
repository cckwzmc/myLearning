package com.toney.istyle.core.biz;

import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.form.ProductBaseForm;

public interface ProductDetailManager {

	void ceateBaseInfo(ProductBaseForm form) throws ManagerException;

}
