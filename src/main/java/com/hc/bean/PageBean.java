package com.hc.bean;

import com.github.pagehelper.Page;
import com.hc.utils.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 用户单击页码时，服务器端返回的数据
 *
 * @param <T>
 */
@Getter
@Setter
@NoArgsConstructor
public class PageBean<T> {
    /**
     * 每页显示的条数
     */
    private Integer pageSize;
    /**
     * 当前的页码
     */
    private Integer pageNum;
    /**
     * 一共有多少条记录
     */
    private Long total;
    /**
     * 一共有多少页
     */
    private Integer pages;
    /**
     * 每一页所显示的数据
     */
    private List<T> records;

    /**
     * 分页请求路径
     */
    private String url;

    /**
     * 将MyBatis的Page对象转换成我们自定义的PageBean对象
     *
     * @param page
     */
    public PageBean(Page<T> page) {
        this.pageSize = page.getPageSize();
        this.pageNum = page.getPageNum();
        this.total = page.getTotal();
        //将Long转换成Integer类型
        long tmp = this.total % this.pageSize == 0 ? this.total / this.pageSize : this.total / this.pageSize + 1;
        this.pages = Long.valueOf(tmp).intValue();
        this.records = page.getResult();
    }

    @Override
    public String toString() {
        //返回当前对象的JSON字符串
        return JsonUtil.obj2String(this);
    }
}
