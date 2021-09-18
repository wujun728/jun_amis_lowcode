
        package com.jun.biz.manager.mapstruct;


        import org.mapstruct.Mapper;
        import org.mapstruct.ReportingPolicy;

import com.jun.biz.manager.model.AdminLoginRecord;
import com.jun.biz.manager.vo.admin.AdminLoginRecordVO;

import java.util.List;

        /**
 * Created on 2020/10/14 18:51
 * <p>
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminLoginRecordConverter {

    List<AdminLoginRecordVO> toVo(List<AdminLoginRecord> entityList);

}
