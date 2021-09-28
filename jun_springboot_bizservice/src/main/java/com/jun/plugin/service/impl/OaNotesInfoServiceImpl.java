package com.jun.plugin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jun.plugin.mapper.OaNotesInfoMapper;
import com.jun.plugin.entity.OaNotesInfoEntity;
import com.jun.plugin.service.OaNotesInfoService;


@Service("oaNotesInfoService")
public class OaNotesInfoServiceImpl extends ServiceImpl<OaNotesInfoMapper, OaNotesInfoEntity> implements OaNotesInfoService {


}