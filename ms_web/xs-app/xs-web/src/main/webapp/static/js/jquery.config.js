$(function() 
{
		
		// homepage rotator
		$('#featured-office').cycle(
			{
				fx:     'scrollLeft',
				timeout: 8000,
				cleartype: 1,
				pause: 1, 
				pauseOnPagerHover: 1,
				pager:'#featured-office-nav'
			});
			
		// homepage rotator
		$('#text-rotate').cycle(
			{
				fx:     'scrollUp',
				timeout: 3000,
				height: 35,
				cleartype: 1,
				pause: 1
			});
	
			$("#catSelect select").change(function() {
			        if (this.value != '') {
			            $("#catSelect").submit();
			        }
			    });
	   //lightbox
			
			$('#office-images a').lightBox();
			
	  // media plugin .media
		$('.media').media( { width: 600, height: 486, type: 'swf' } );
		$('.media-playlist').media( { width: 600, height: 376, type: 'swf' } );

	  // hides directory and extra form fields
	  $("#directory").hide();
	  //assigns toggles
	  $("#expand a").click(function()
	  {
	    $("#directory").slideToggle(600);
	  });
	

		
	  $('.popeye').popeye(
		{
			direction: 'right',
			stageW:     '180px',
			stageH: '130px' 
		});
		
		$("#side-search, #site-search").autocomplete(op_cat_list, {matchContains: true});
		
		
		
		var tabContainers = $('#tabServices, #tabReviews, #tabDescription, #tabVideos, #tabOther');
		
		$('.leave-review').click(function(){
			tabContainers.hide().filter('#tabReviews').show();
			$('div.tabs ul.tabNavigation a').removeClass('selected');
			$('div.tabs ul.tabNavigation a.review-tab').addClass('selected');
			return false;
		}).filter(':first').click();

		$('div.tabs ul.tabNavigation a').click(function () {
		        tabContainers.hide().filter(this.hash).show();

		        $('div.tabs ul.tabNavigation a').removeClass('selected');
		        $(this).addClass('selected');

		        return false;
		    }).filter(':first').click();
		
			//open windows without embedding target="_blank"
						
			$('a[rel="external"]').each(function() {
			$(this).attr('target', '_blank');
			});
});
