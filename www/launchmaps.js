window.launchmaps = function(onSuccess, onError, latitude, longitude) {
	cordova.exec(onSuccess, onError, "LaunchMaps", "launch", [latitude, longitude]);
};

module.exports = window.launchmaps;
