package com.fastcampus.ch3.domain;

public class PageHandler {
    private int totalCnt; // 전체게시물개수
    private int pageSize=20; // 한페이지당 게시물 출력개수
    private int pageCnt; // 전체 페이지의 개수

    private int naviSize = 10; // navi 한페지 사이즈 개수
    private int naviStart =1; // navi 페이지 시작점
    private int naviEnd; // navi 페이지 끝
    private int curPage; // 현재 페이지
    private boolean showPrev; // 이전페이지로 이동 <
    private boolean showNext; // 다음페이지로 이동 >


    public PageHandler(int totalCnt, int curPage, int pageSize){
        this.totalCnt = totalCnt;
        this.curPage = curPage;
        this.pageSize = pageSize;

        // 계산
        pageCnt = totalCnt / pageSize + (totalCnt % pageSize==0 ? 0 : 1); // 페이지 개수 = 전체개시물개수/한페이지당 게시물출력개수
        naviStart = (curPage-1) / naviSize * naviSize + 1; // 페이지 시작
        naviEnd = (naviStart + naviSize- 1); // 페이지 끝
        naviEnd = naviEnd > pageCnt ? pageCnt : naviEnd; // 페이지끝 > 페이지개수 ? 페이지개수 : 페이지끝
        showPrev = naviStart !=1;
        showNext = naviEnd != pageCnt;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCnt() {
        return pageCnt;
    }

    public void setPageCnt(int pageCnt) {
        this.pageCnt = pageCnt;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getNaviStart() {
        return naviStart;
    }

    public void setNaviStart(int naviStart) {
        this.naviStart = naviStart;
    }

    public int getNaviEnd() {
        return naviEnd;
    }

    public void setNaviEnd(int naviEnd) {
        this.naviEnd = naviEnd;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    void print(){
        System.out.println("curPage:"+curPage);
        System.out.print(showPrev ? "[prev]":"");
        for(int i=naviStart; i<=naviEnd; i++){
            System.out.print(i+" ");
        }
        System.out.println(showNext ? "[Next]": "");
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCnt=" + totalCnt +
                ", pageSize=" + pageSize +
                ", pageCnt=" + pageCnt +
                ", naviSize=" + naviSize +
                ", naviStart=" + naviStart +
                ", naviEnd=" + naviEnd +
                ", curPage=" + curPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
