
var app = angular.module("myTestApp", []);

app.directive("w3TestDirective", function() {
	return {
		template : "I was made in a directive constructor!"
	};
});