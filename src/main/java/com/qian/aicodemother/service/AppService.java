package com.qian.aicodemother.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.qian.aicodemother.model.dto.app.AppQueryRequest;
import com.qian.aicodemother.model.entity.App;
import com.qian.aicodemother.model.entity.User;
import com.qian.aicodemother.model.vo.AppVO;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 应用 服务层
 *
 * @author <a href="https://github.com/themilky-way">程序员AndyQian</a>
 */
public interface AppService extends IService<App> {


    /**
     * 通过对话生成应用代码
     *
     * @param appId     应用 ID
     * @param message   提示词
     * @param loginUser 登录用户
     * @return 流式代码生成结果
     */
    Flux<String> chatToGenCode(Long appId, String message, User loginUser);

    /**
     * 部署应用
     *
     * @param appId     应用 ID
     * @param loginUser 登录用户
     * @return 可访问的部署地址
     */
    String deployApp(Long appId, User loginUser);

    /**
     * 异步生成应用截图并更新封面
     *
     * @param appId  应用 ID
     * @param appUrl 应用访问 URL
     */
    void generateAppScreenshotAsync(Long appId, String appUrl);

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
