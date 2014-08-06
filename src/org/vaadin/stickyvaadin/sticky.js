org_vaadin_stickyvaadin_Sticky = function() {
	
	var topSpacingPx = 0;
	var stickerid = "sticker";
	
	this.makeSticky = function() {
		$("#"+stickerid).sticky({topSpacing:topSpacingPx});
		console.log("should be sticky now");
    };

	this.makeUnSticky = function() {
		$("#"+stickerid).unstick();
		console.log("should be UNsticky now");
	};
	
	this.onStateChange = function() {
		stickerid = this.getState().stickyId;
		topSpacingPx = this.getState().topSpacingInPx;
		console.log("onStateChange");
	};
};