<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${namespace}">
  
  <resultMap type='${modelName}' id='${modelName}Map'>
    ${resultMap}  </resultMap>
  
  <sql id="Base_Column_List" >
    ${columns}
  </sql>
  
  <select id='get${modelName}ListPage' parameterType="${modelName}" resultMap='${modelName}Map'>
    select 
      <include refid="Base_Column_List" />
    from ${tableName} 
    ${whereColumsIfPage}
    order by stamp_updated desc
  </select>
  
  <select id='getAll${modelName}' parameterType="${modelName}" resultMap='${modelName}Map'>
    select
      <include refid="Base_Column_List" />
    from ${tableName} 
    ${whereColumsIf}
    order by stamp_updated desc
  </select>
  
  <insert id="create${modelName}" parameterType="${modelName}">
    insert into ${tableName} 
    ${insertColumsIf}
    ${insertValuesIf}
  </insert>
  
  <update id="update${modelName}" parameterType="${modelName}">
    update ${tableName} 
    ${updateColumsIf}
    where ${id}
  </update>
  
  <delete id='delete${modelName}' parameterType='long'>
    delete from ${tableName} where ${id}
  </delete>
  
</mapper>