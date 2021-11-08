<%@ taglib prefix="tho" uri="/thorben"%>
<footer id="page-footer">
	<div class="scial_media" style="text-align:center">
		<a target="_blank" href="https://www.xing.com/profile/Thorben_Dierkes/cv" class="fa fa-3x fa-xing"></a>    
		<a target="_blank" href="https://www.facebook.com/thorben.dierkes" class="fa fa-3x fa-facebook-square"></a>    
		<a target="_blank" href="https://www.instagram.com/tho34f/?hl=de" class="fa fa-3x fa-instagram"></a>
	</div>
	<div class="contact" style="text-align:center">
		<p style="padding-top:11px;"><tho:out value="global.date"/>: ${formatDate}</p>
		<div class="row" style="padding-bottom:11px;">
			<div class="col-md-6">
				<p><tho:out value="global.hompage.copyrith"/> 2015</p>
			</div>
			<div class="col-md-6" style="width:700px; padding-left:0px; padding-right:0px;">
				<a class="datenschutz" href="/thorben-dierkes/datenschutz"><tho:out value="global.hompage.data.protection"/></a> | 
				<a class="impressum" href="/thorben-dierkes/impressum"><tho:out value="global.hompage.imprint"/></a>
			</div>
		</div>
	</div>
</footer>