package com.qian.aicodemother.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.qian.aicodemother.model.dto.app.AppQueryRequest;
import com.qian.aicodemother.model.entity.App;
import com.qian.aicodemother.model.vo.AppVO;

import java.util.List;

/**
 * 应用 服务层
 *
 * @author <a href="https://github.com/themilky-way">程序员AndyQian</a>
 */
public interface AppService extends IService<App> {


    /**
     * 获取应用封装类
     */
    AppVO getAppVO(App app);

    /**
     * 获取应用封装类列表
     */
    List<AppVO> getAppVOList(List<App> appList);

    /**
     * 构造应用查询条件
     */
    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);
}
