package ${package.Converter};

import ${package.Condition}.${condition};
import ${package.DTO}.${dto};
import ${package.DTO}.${pageQuery};
import ${package.Entity}.${entity};
import ${package.VO}.${vo};
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ${table.comment!}转换类
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper
public interface ${converter} {

    ${converter} INSTANCE = Mappers.getMapper(${converter}.class);

    /**
     * convert dto to po
     *
     * @param ${dto?uncap_first}
     * @return
     */
    ${entity} dtoToPo(${dto} ${dto?uncap_first});

    /**
     * convert po to vo
     *
     * @param ${entity?uncap_first}
     * @return
     */
    ${vo} poToVo(${entity} ${entity?uncap_first});

    /**
     * convert po to vo
     *
     * @param ${entity?uncap_first}List
     * @return
     */
    List<${vo}> poToVo(List<${entity}> ${entity?uncap_first}List);

    /**
     * convert po to vo
     *
     * @param page
     * @return
     */
    default IPage<${vo}> poToVo(IPage<${entity}> page) {
        return page.convert(${converter}.INSTANCE::poToVo);
    }

    /**
     * convert query to condition
     *
     * @param pageQuery
     * @return
     */
    ${condition} queryToCondition(${pageQuery} pageQuery);

}
