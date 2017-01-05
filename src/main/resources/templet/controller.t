${copyRight}
package ${pgName}.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.shuzun.manager.model.SysSuccess;
import net.shuzun.manager.page.PagerJsonResponse;
import ${pgName}.model.${modelName};
import ${pgName}.service.${modelName}Service;
import net.shuzun.manager.page.Pager;

${classNote}
@Controller
@RequestMapping("/${modelValueName}/*")
public class ${modelName}Controller {
    
    @Autowired
    private ${modelName}Service ${modelValueName}Service;
    
    @RequestMapping(value = "/view")
    public ModelAndView view${modelName}List() {
        ModelAndView mav = new ModelAndView("admin/");
        return mav;
    }
    
    @RequestMapping(value = "/list")
    @ResponseBody
    public PagerJsonResponse<${modelName}> get${modelName}List(Pager pager, ${modelName} ${modelValueName}) {
        List<${modelName}> list = ${modelValueName}Service.get${modelName}ListPage(pager, ${modelValueName});
        PagerJsonResponse<${modelName}> response = new PagerJsonResponse<${modelName}>(list, pager);
        return response;
    }
    
    @RequestMapping(value = "/all")
    @ResponseBody
    public PagerJsonResponse<${modelName}> getAll${modelName}(${modelName} ${modelValueName}) {
        List<${modelName}> list = ${modelValueName}Service.getAll${modelName}(${modelValueName});
        Pager pager = new Pager(list.size());
        PagerJsonResponse<${modelName}> response = new PagerJsonResponse<${modelName}>(list, pager);
        return response;
    }
    
    @RequestMapping(value="/save")
    @ResponseBody
    public SysSuccess create${modelName}(@ModelAttribute("${modelValueName}") ${modelName} ${modelValueName}) {        
        if (${modelValueName}Service.create${modelName}(${modelValueName}) == 1) {
            return new SysSuccess(true, "创建成功！");
        } else {
            return new SysSuccess(false, "创建失败！");
        }
    }
    
    @RequestMapping(value="/update")
    @ResponseBody
    public SysSuccess update${modelName}(@ModelAttribute("${modelValueName}") ${modelName} ${modelValueName}) {      
        if (${modelValueName}Service.update${modelName}(${modelValueName}) == 1) {
            return new SysSuccess(true, "修改成功！");
        } else {
            return new SysSuccess(false, "修改失败！");
        }
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    public SysSuccess delete${modelName}(@RequestParam long id) {       
        int rowNum = ${modelValueName}Service.delete${modelName}(id);
        if (rowNum == 1) {
            return new SysSuccess(true, "删除成功！");
        } else {
            return new SysSuccess(false, "删除失败！");
        }
    }

}