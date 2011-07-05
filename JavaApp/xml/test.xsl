<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template name="checkbox-with-hidden-input">
		<xsl:param name="id" />
		<xsl:param name="value" />
		<xsl:param name="checked" />
		<xsl:param name="hidden-param-name" />
		<xsl:param name="hidden-param-id" />
		<script>
			function saveCheckbox(value,targetId)
			{
			target = document.getElementById(targetId);
			target.value = value;
			}
		</script>
		<input type="checkbox" value="{$value}" checked="{$checked}"
			onChange="saveCheckbox({$value},{$hidden-param-id})"></input>
		<input type="hidden" name="{$hidden-param-name}" id="{$hidden-param-id}"
			value="{$value}"></input>
		&#160;
	</xsl:template>
	<xsl:template match="/">
		<HTML>
			<BODY>
				<xsl:call-template name="checkbox-with-hidden-input">
					<xsl:with-param name="id" select="'100'"></xsl:with-param>
					<xsl:with-param name="value" select="'200'"></xsl:with-param>
					<xsl:with-param name="checked" select="'true'"></xsl:with-param>
					<xsl:with-param name="hidden-param-name" select="'300'"></xsl:with-param>
					<xsl:with-param name="hidden-param-id" select="'400'"></xsl:with-param>
				</xsl:call-template>
			</BODY>
		</HTML>
	</xsl:template>
</xsl:stylesheet>
