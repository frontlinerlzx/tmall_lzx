package com.frontlinerlzx.tmall_lzx.util;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author lzx
 * @create 2019-08-27-21:39
 * <p>
 * 此类重新包装了Page类，添入确定导航栏范围
 */
public class PageNavigator<T> {
    //分页的工具类
    Page<T> pageFromJPA;

    //导航栏的指定显示数量
    int navigatePages;

    //总共页码数
    int totalPages;

    //当前页码数
    int number;

    //总共的条目数量
    long totalElements;

    //指定显示的条目数量
    int size;

    //当页所含的条目数量
    int numberOfElements;

    //内容
    List<T> content;

    //是否还有内容
    boolean isHasContent;

    //是否首页
    boolean first;

    //是否尾页
    boolean last;

    //是否还有下一页
    boolean isHasNext;

    //是否还有前一页
    boolean isHasPrevious;

    //导航栏所需要遍历的数组
    int[] navigatepageNums;

    //为了缓存使用的空构造函数
    public PageNavigator() {

    }

    //重新包装pageFromJPA对象的数据交给Page4Navigator对象
    public PageNavigator(Page<T> pageFromJPA, int navigatePages) {
        this.pageFromJPA = pageFromJPA;

        this.navigatePages = navigatePages;

        totalPages = pageFromJPA.getTotalPages();

        number = pageFromJPA.getNumber();

        totalElements = pageFromJPA.getTotalElements();

        size = pageFromJPA.getSize();

        numberOfElements = pageFromJPA.getNumberOfElements();

        content = pageFromJPA.getContent();

        isHasContent = pageFromJPA.hasContent();

        first = pageFromJPA.isFirst();

        last = pageFromJPA.isLast();

        isHasNext = pageFromJPA.hasNext();

        isHasPrevious = pageFromJPA.hasPrevious();

        //开始计算导航栏的显示页码范围
        calculateNavigatepageNums();
    }

    //开始计算导航栏的显示页码范围
    public void calculateNavigatepageNums() {
        int[] navigatepageNums;
        int totalPages = this.getTotalPages();
        int number = this.getNumber();

        if (navigatePages >= totalPages) {//当指定的导航栏显示数量大于总页码数（这样无需根据当前页码来改变显示导航栏的范围）
            navigatepageNums = new int[totalPages];
            for (int i = 0; i < totalPages; i++)
                navigatepageNums[i] = i + 1;
        } else {//当指定导航栏页码显示数量小于总页码，需要根据当前页码来确定当前显示导航栏的内容
            int startNum = number - navigatePages / 2; //开始页码
            int endNum = number + navigatePages / 2;//结束页码
            navigatepageNums = new int[navigatePages];
            if (startNum < 1) {//当开始页码小于0
                startNum = 1;
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > totalPages) {//当结束页码大于总页码
                endNum = totalPages;
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {//处于中间，默认状态
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }

            }

        }
        this.navigatepageNums = navigatepageNums;

    }


    public Page<T> getPageFromJPA() {
        return pageFromJPA;
    }

    public void setPageFromJPA(Page<T> pageFromJPA) {
        this.pageFromJPA = pageFromJPA;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isHasContent() {
        return isHasContent;
    }

    public void setHasContent(boolean hasContent) {
        isHasContent = hasContent;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isHasNext() {
        return isHasNext;
    }

    public void setHasNext(boolean hasNext) {
        isHasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return isHasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        isHasPrevious = hasPrevious;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }
}
