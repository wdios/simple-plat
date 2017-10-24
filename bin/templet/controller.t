${copyRight}
package ${pgName}.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ${pgName}.model.SysSuccess;
import ${pgName}.model.SysUser;
import ${pgName}.page.Pager;
import ${pgName}.page.PagerJsonResponse;
import ${pgName}.sql.SqlHelper;

${classNote}
@Controller
@RequestMapping("/${modelName}/*")
public class ${className}Controller {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @RequestMapping(value = "/view")
    public ModelAndView viewPage(ServletRequest request, ServletResponse response) {
        ModelAndView mav = new ModelAndView("admin/${tableName}");
        return mav;
    }
    
    @RequestMapping(value = "/list")
    @ResponseBody
    public PagerJsonResponse<${className}> getList(Pager pager) {
        SqlHelper aSqlHelper = new SqlHelper(new ${className}(), "${tableName}");
        String sqlStr = aSqlHelper.getQuerySQLPage("", "", 1, 12);
        List<${className}> list = jdbcTemplate.query(sqlStr, new BeanPropertyRowMapper<${className}>(${className}.class));
        PagerJsonResponse<${className}> response = new PagerJsonResponse<${className}>(list, pager);
        return response;
    }
    
    @RequestMapping(value = "/all")
    @ResponseBody
    public List<${className}> getAll() {
        SqlHelper aSqlHelper = new SqlHelper(new ${className}(), "${tableName}");
        String sqlStr = aSqlHelper.getQuerySQLAll("", "");
        List<${className}> list = jdbcTemplate.query(sqlStr, new BeanPropertyRowMapper<${className}>(${className}.class));
        return list;
    }
    
    @RequestMapping(value="/create")
    @ResponseBody
    public SysSuccess create(@ModelAttribute("${className}") ${className} ${modelName}) {
        SqlHelper aSqlHelper = new SqlHelper(${modelName}, "${tableName}");
        String sqlStr = aSqlHelper.getInsertSQL();
        
        int opNum = jdbcTemplate.update(sqlStr, aSqlHelper.getParams());
        
        if (opNum == 1) {
            return new SysSuccess(true, "创建成功！");
        } else {
            return new SysSuccess(false, "创建失败！");
        }
    }
    
    @RequestMapping(value="/update")
    @ResponseBody
    public SysSuccess update(@ModelAttribute("${modelName}") ${className} sysUser) {
        SqlHelper aSqlHelper = new SqlHelper(${modelName}, "${tableName}");
        String sqlStr = aSqlHelper.getUpdateSQL();
        
        int opNum = jdbcTemplate.update(sqlStr, aSqlHelper.getParams());
        
        if (opNum == 1) {
            return new SysSuccess(true, "修改成功！");
        } else {
            return new SysSuccess(false, "修改失败！");
        }
    }
    
    @RequestMapping(value="/delete")
    @ResponseBody
    public SysSuccess delete(@RequestParam("id") long id) {
        ${className} a${className} = new ${className}();
        a${modelName}.setId(id);
        
        SqlHelper aSqlHelper = new SqlHelper(a${className}, "${tableName}");
        String sqlStr = aSqlHelper.getDeleteSQL("", "");
        
        int opNum = jdbcTemplate.update(sqlStr, aSqlHelper.getParams());
        
        if (opNum == 1) {
            return new SysSuccess(true, "删除成功！");
        } else {
            return new SysSuccess(false, "删除失败！");
        }
    }

}