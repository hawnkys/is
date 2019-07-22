<?xml version="1.0" encoding="UTF-8"?>
<html xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xsl:version="1.0">
	<head>
		<link rel="stylesheet" type="text/css" href="style.css"/>
		<title>Destaques</title>
	</head> 
	<body>
		<div class="container">
			<a href="http://www.standvirtual.com" target="_blank">
				<img src="logo-standvirtual.png" class="logo"/>
			</a>
			<table>
				<tr>
					<th></th>
					<th>ID</th>
					<th>Brand</th>
					<th>Model</th>
					<th>Fuel</th>
					<th>Reg. Month</th>
					<th>Reg. Year</th>
					<th>Distance</th>
					<th>Power</th>
					<th>CC</th>
					<th>Price</th>
				</tr>
				<xsl:for-each select="//car">
					<tr onclick="window.open('{url}')">
						<td><img src="{image}" width="200px"/></td>
						<td><xsl:value-of select="@id"/></td>
						<td><xsl:value-of select="brand"/></td>
						<td><xsl:value-of select="model"/></td>
						<td><xsl:value-of select="fuel"/></td>
						<td><xsl:value-of select="month"/></td>
						<td><xsl:value-of select="year"/></td>
						<td><xsl:value-of select="distance"/>&#160;<xsl:value-of select="distance/@unit"/></td>
						<td><xsl:value-of select="power"/>&#160;<xsl:value-of select="power/@unit"/></td>
						<td><xsl:value-of select="cc"/>&#160;<xsl:value-of select="cc/@unit"/></td>
						<td><xsl:value-of select="price"/>&#160;<xsl:value-of select="price/@unit"/></td>
					</tr>
				</xsl:for-each>
			</table>
		</div>
	</body>
</html>
