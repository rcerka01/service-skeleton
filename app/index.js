const generators = require('yeoman-generator');
const colors = require('chalk');

module.exports = generators.Base.extend({
  prompting: function () {
    return this.prompt([
      {
        type    : 'input',
        name    : 'serviceName',
        message : 'Service Name',
        default : 'service'
      }
    ]).then(function (answers) {

      var unprefixedServiceName = answers.serviceName;
      var packageName = answers.serviceName;
      var camelCaseName = answers.serviceName;
      var capitalisedName = answers.serviceName;

      this.log('Service name: ', answers.serviceName);
      this.log('Package name: ', packageName);
      this.log('Camel case name: ', camelCaseName)
      this.log('Capitalised name: ', capitalisedName)

      this.context = {
        serviceName: answers.serviceName,
        unprefixedServiceName: unprefixedServiceName,
        packageName: packageName,
        camelCaseName: camelCaseName,
        capitalisedName: capitalisedName
      };

    }.bind(this));
  },

  copyFiles: function () {
    this.log('Creating service files...');

    this.fs.copyTpl(this.templatePath('project/build.properties'), this.destinationPath('project/build.properties'), this.context);
    this.fs.copyTpl(this.templatePath('project/IntegrationTests.scala'), this.destinationPath('project/IntegrationTests.scala'), this.context);
    this.fs.copyTpl(this.templatePath('project/plugins.sbt'), this.destinationPath('project/plugins.sbt'), this.context);

    this.fs.copyTpl(this.templatePath('src/it'), this.destinationPath('src/it'), this.context);
    this.fs.copyTpl(this.templatePath('src/main/resources'), this.destinationPath('src/main/resources'), this.context);

    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/ServiceNameContext.scala'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/' + this.context.capitalisedName + 'Context.scala'),
      this.context);
    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/ServiceNameService.scala'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/' + this.context.capitalisedName + 'Service.scala'),
      this.context);

    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/api/directives'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/api/directives'),
      this.context);
    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/api/error/ServiceNameExceptionHandler.scala'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/api/error/' + this.context.capitalisedName + 'ExceptionHandler.scala'),
      this.context);
    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/api/params'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/api/params'),
      this.context);
    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/api/rejection/ServiceNameRejection.scala'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/api/rejection/' + this.context.capitalisedName + 'Rejection.scala'),
      this.context);
    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/api/rejection/ServiceNameRejectionHandler.scala'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/api/rejection/' + this.context.capitalisedName + 'RejectionHandler.scala'),
      this.context);
    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/api/validation'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/api/validation'),
      this.context);
    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/api/Api.scala'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/api/Api.scala'),
      this.context);
    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/api/ItemApi.scala'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/api/ItemApi.scala'),
      this.context);
    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/api/StatusApi.scala'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/api/StatusApi.scala'),
      this.context);

    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/config'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/config'),
      this.context);
    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/datasource'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/datasource'),
      this.context);
    this.fs.copyTpl(this.templatePath('src/main/scala/servicename/domain'),
      this.destinationPath('src/main/scala/' + this.context.packageName + '/domain'),
      this.context);

    this.fs.copyTpl(this.templatePath('src/test/scala/servicename'),
      this.destinationPath('src/test/scala/' + this.context.packageName),
      this.context);
    this.fs.copyTpl(this.templatePath('src/test/scala/util'),
      this.destinationPath('src/test/scala/util'),
      this.context);

    this.fs.copyTpl(this.templatePath('.gitignore'), this.destinationPath('.gitignore'), this.context);
    this.fs.copyTpl(this.templatePath('build.sbt'), this.destinationPath('build.sbt'), this.context);
    this.fs.copyTpl(this.templatePath('project.json'), this.destinationPath('project.json'), this.context);
    this.fs.copyTpl(this.templatePath('README.md'), this.destinationPath('README.md'), this.context);
    this.fs.copyTpl(this.templatePath('dependency-suppressions.xml'), this.destinationPath('dependency-suppressions.xml'), this.context);
    this.fs.copyTpl(this.templatePath('scalastyle-config.xml'), this.destinationPath('scalastyle-config.xml'), this.context);
  }

});
