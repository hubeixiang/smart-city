package com.sct.service.core.web.support.collection;

import com.sct.service.core.web.support.collection.pages.PageResponse;
import com.sct.service.core.web.support.collection.pages.Paging;

/**
 * 分页返回的结果
 */
public class PageResultVO implements ResultPageVO {
    private PageResponse paging;
    private ResultVO result;

    public static PageResultVO of() {
        PageResultVO pageResultVO = new PageResultVO();
        return pageResultVO;
    }

    public static PageResultVO of(PageResponse paging, ResultVO resultVO) {
        PageResultVO pageResultVO = new PageResultVO();
        pageResultVO.setPaging(paging);
        pageResultVO.setResult(resultVO);
        return pageResultVO;
    }

    @Override
    public Paging getPaging() {
        return paging;
    }

    public void setPaging(PageResponse paging) {
        this.paging = paging;
    }

    @Override
    public ResultVO getResult() {
        return result;
    }

    public void setResult(ResultVO result) {
        this.result = result;
    }
}
