package com.mybatis.util;

import java.util.List;

/**
 * Description:参考：http://bbs.csdn.net/topics/360010907 User: Darlen liu Date:
 * 14-6-24 Time: 下午10:26
 */
public class Pager<T> {
    private List<T> list; // 对象记录结果集
    private int totalRecode = 0; // 总记录数
    private int pageCount = 10; // 每页显示记录数
    private int pageRecode = 1; // 总页数
    private int curPageNum = 1; // 当前页

    private boolean isFirstPage = false; // 是否为第一页
    private boolean isLastPage = false; // 是否为最后一页
    private boolean hasPreviousPage = false; // 是否有前一页
    private boolean hasNextPage = false; // 是否有下一页

    private int navigatePages = 8; // 导航页码数
    private int[] navigatePageNumbers; // 所有导航页号

    public Pager(int totalRecode, int curPageNum) {
        init(totalRecode, curPageNum, pageCount);
    }

    public Pager(int totalRecode, int curPageNum, int pageCount) {
        init(totalRecode, curPageNum, pageCount);
    }

    private void init(int totalRecode, int curPageNum, int pageCount) {
        // 设置基本参数
        this.totalRecode = totalRecode;
        this.pageCount = pageCount;
        this.pageRecode = (this.totalRecode - 1) / this.pageCount + 1;

        // 根据输入可能错误的当前号码进行自动纠正
        if (curPageNum < 1) {
            this.curPageNum = 1;
        } else if (curPageNum > this.pageRecode) {
            this.curPageNum = this.pageRecode;
        } else {
            this.curPageNum = curPageNum;
        }

        // 基本参数设定之后进行导航页面的计算
        calcNavigatePageNumbers();

        // 以及页面边界的判定
        judgePageBoudary();
    }

    /**
     * 计算导航页
     */
    private void calcNavigatePageNumbers() {
        // 当总页数小于或等于导航页码数时
        if (pageRecode <= navigatePages) {
            navigatePageNumbers = new int[pageRecode];
            for (int i = 0; i < pageRecode; i++) {
                navigatePageNumbers[i] = i + 1;
            }
        } else { // 当总页数大于导航页码数时
            navigatePageNumbers = new int[navigatePages];
            int startNum = curPageNum - navigatePages / 2;
            int endNum = curPageNum + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;
                // (最前navigatePages页
                for (int i = 0; i < navigatePages; i++) {
                    navigatePageNumbers[i] = startNum++;
                }
            } else if (endNum > pageRecode) {
                endNum = pageRecode;
                // 最后navigatePages页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatePageNumbers[i] = endNum--;
                }
            } else {
                // 所有中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatePageNumbers[i] = startNum++;
                }
            }
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = curPageNum == 1;
        isLastPage = curPageNum == pageRecode && curPageNum != 1;
        hasPreviousPage = curPageNum > 1;
        hasNextPage = curPageNum < pageRecode;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 得到当前页的内容
     *
     * @return {List}
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 得到记录总数
     *
     * @return {int}
     */
    public int getTotalRecode() {
        return totalRecode;
    }

    /**
     * 得到每页显示多少条记录
     *
     * @return {int}
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * 得到页面总数
     *
     * @return {int}
     */
    public int getPageRecode() {
        return pageRecode;
    }

    /**
     * 得到当前页号
     *
     * @return {int}
     */
    public int getCurPageNum() {
        return curPageNum;
    }

    /**
     * 得到所有导航页号
     *
     * @return {int[]}
     */
    public int[] getNavigatePageNumbers() {
        return navigatePageNumbers;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public boolean hasPreviousPage() {
        return hasPreviousPage;
    }

    public boolean hasNextPage() {
        return hasNextPage;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[").append("total=").append(totalRecode).append(",pages=")
                .append(pageRecode).append(",pageNumber=").append(curPageNum)
                .append(",pageCount=").append(pageCount).append(",isFirstPage=")
                .append(isFirstPage).append(",isLastPage=").append(isLastPage)
                .append(",hasPreviousPage=").append(hasPreviousPage)
                .append(",hasNextPage=").append(hasNextPage)
                .append(",navigatePageNumbers=");
        int len = navigatePageNumbers.length;
        if (len > 0)
            sb.append(navigatePageNumbers[0]);
        for (int i = 1; i < len; i++) {
            sb.append(" " + navigatePageNumbers[i]);
        }
        sb.append(",list.size=" + list.size());
        sb.append("]");
        return sb.toString();
    }
}
