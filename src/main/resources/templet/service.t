${copyRight}
package ${pgName}.service;

import java.util.List;

import ${pgName}.model.${modelName};
import net.shuzun.manager.page.Pager;

public interface ${modelName}Service {

    public List<${modelName}> get${modelName}ListPage(Pager pager, ${modelName} ${modelValueName});
    
    public List<${modelName}> getAll${modelName}(${modelName} ${modelValueName});
    
    public ${modelName} getA${modelName}(long id);
    
    public int create${modelName}(${modelName} ${modelValueName});
    
    public int update${modelName}(${modelName} ${modelValueName});
    
    public int delete${modelName}(long id);
    
}
