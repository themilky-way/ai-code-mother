package com.qian.aicodemother.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.qian.aicodemother.model.entity.App;
import com.qian.aicodemother.mapper.AppMapper;
import com.qian.aicodemother.service.AppService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author <a href="https://github.com/themilky-way">程序员AndyQian</a>
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App>  implements AppService{

}
