org_vaadin_stickyvaadin_Sticky = function() {
	
	var stickerid = "sticker";
	
	this.makeSticky = function() {
		$("#"+stickerid).sticky({topSpacing:0});
		console.log("should be sticky now");
    };

	this.makeUnSticky = function() {
		$("#"+stickerid).unstick();
		console.log("should be UNsticky now");
	};
	
	this.onStateChange = function() {
		stickerid = this.getState().stickyId;
		console.log("onStateChange");
	};
};