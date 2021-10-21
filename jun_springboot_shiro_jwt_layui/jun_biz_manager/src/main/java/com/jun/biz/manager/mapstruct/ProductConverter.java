
package com.jun.biz.manager.mapstruct;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.jun.biz.common.search.document.ProductDoc;
import com.jun.biz.manager.dto.product.CreateProductDTO;
import com.jun.biz.manager.dto.product.ModifyProductDTO;
import com.jun.biz.manager.model.Product;
import com.jun.biz.manager.vo.product.ProductVO;

import java.util.List;

/**
 * Created on 2020/10/14 18:51
 * <p>
 *
 * 
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductConverter {

    List<ProductVO> toVo(List<Product> entityList);

    ProductVO toVo(Product product);

    Product createDtoToEntity(CreateProductDTO dto);

    Product modifyDtoToEntity(ModifyProductDTO dto);

    ProductDoc toDoc(Product product);
}
