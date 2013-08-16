module.exports = function(grunt) {

  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
	connect: {
		server: {
		  	options: {
			    port: 9001,
			    base: 'docs',
			    keepalive:true
			}
		}
	},
    ngdocs: {
	  options: {
	    dest: 'docs',
	    scripts: ['http://code.angularjs.org/1.0.7/angular.min.js'],
	    html5Mode: false,
	    startPage: '/api',
	    title: 'KT SMCP AUI',
	    // analytics: {
	    //       account: 'UA-08150815-0',
	    //       domainName: 'my-domain.com'
	    // },
	    // discussions: {
	    //       shortName: 'my',
	    //       url: 'http://my-domain.com',
	    //       dev: false
	    // }
	  },
	  api: {
	    src: ['js/admin/userMgt.js'],
	    title: 'API Reference'
	  }
	}
  });

  grunt.loadNpmTasks('grunt-ngdocs');
  grunt.loadNpmTasks('grunt-contrib-connect');

  // grunt.registerTask('test', ['jshint', 'qunit']);

  grunt.registerTask('default', ['ngdocs']);

};