org_vaadin_stickyvaadin_Sticky = function() {
	
	var topSpacingPx = 0;
	var stickerid = "sticker";
	var originalZIndex = 0;
	
	this.makeSticky = function() {
		var stickyElement = $("#"+stickerid)
		originalZIndex = stickyElement[0].style.zIndex;
		console.log("zindex was: "+originalZIndex);
		stickyElement[0].style.zIndex = 100000;
		stickyElement.sticky({topSpacing:topSpacingPx});
		console.log("should be sticky now");
    };

	this.makeUnSticky = function() {
		var stickyElement = $("#"+stickerid)
		stickyElement[0].style.zIndex = originalZIndex;
		stickyElement.unstick();
		console.log("should be UNsticky now");
	};
	
	this.onStateChange = function() {
		stickerid = this.getState().stickyId;
		topSpacingPx = this.getState().topSpacingInPx;
		console.log("onStateChange");
	};
};