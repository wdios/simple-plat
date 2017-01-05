${copyRight}
package ${pgName}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${pgName}.model.${modelName};
import ${pgName}.dao.${modelName}DAO;
import ${pgName}.service.${modelName}Service;
import net.shuzun.manager.page.Pager;

${classNote}
@Transactional
@Service("${modelValueName}Service")
public class Default${modelName}Service implements ${modelName}Service {

    @Autowired
    private ${modelName}DAO ${modelValueName}DAO;
    
    @Override
    public List<${modelName}> get${modelName}ListPage(Pager pager, ${modelName} ${modelValueName}) {
        return ${modelValueName}DAO.get${modelName}ListPage(pager, ${modelValueName});
    }
    
    @Override
    public List<${modelName}> getAll${modelName}(${modelName} ${modelValueName}) {
        return ${modelValueName}DAO.getAll${modelName}(${modelValueName});
    }
    
    @Override
    public ${modelName} getA${modelName}(long id) {
        return ${modelValueName}DAO.getA${modelName}(id);
    }
    
    @Override
    public int create${modelName}(${modelName} ${modelValueName}) {
        return ${modelValueName}DAO.create${modelName}(${modelValueName});
    }
    
    @Override
    public int update${modelName}(${modelName} ${modelValueName}) {
        return ${modelValueName}DAO.update${modelName}(${modelValueName});
    }
    
    @Override
    public int delete${modelName}(long id) {
        return ${modelValueName}DAO.delete${modelName}(id);
    }

}