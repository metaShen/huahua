	
	// 自动生成区域开始
	public static enum FieldOf${defaultTemplate.entityName} {
		ID, 
		<#list defaultTemplate.fieldlist as field>
		${field}, 
		</#list>
	}
	// 自动生成区域结束
	