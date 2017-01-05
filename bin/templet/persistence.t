${copyRight}
package ${pgName}.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ${pgName}.model.${modelName};
import net.shuzun.manager.page.Pager;

${classNote}
public interface ${modelName}DAO {
    
    public List<${modelName}> get${modelName}ListPage(@Param("page") Pager pager,
            @Param("${modelValueName}") ${modelName} ${modelValueName});
    
    public List<${modelName}> getAll${modelName}(${modelName} ${modelValueName});
    
    public ${modelName} getA${modelName}(long id);
    
    public int create${modelName}(${modelName} ${modelValueName});
    
    public int update${modelName}(${modelName} ${modelValueName});
    
    public int delete${modelName}(long id);
    
}
