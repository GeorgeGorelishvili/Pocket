package org.george.pocket.db.common;

public class Paging {

    // TODO [GG] calibrate max results
    private int offset;

    private int limit;

    private int pageSize = 20;

    public Paging() {
        this.limit = pageSize;
    }

    public Paging(int pageSize) {
        this.limit = pageSize;
    }

    public Paging getNext() {
        Paging paging = new Paging();
        paging.setOffset(limit);
        paging.setLimit(limit + pageSize);
        return paging;
    }

    public void update() {
        offset = limit;
        limit += pageSize;
    }

    public int getPageNumber() {
        return limit / pageSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
