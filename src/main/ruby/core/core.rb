require 'java'
require 'jruby'

require_relative 'config_struct'

java_import 'com.mercuryirc.client.script.ScriptManager'
java_import 'com.mercuryirc.client.script.ScriptDescriptor'

def config(&block)
  @config = ConfigStruct.new
  @config.instance_eval &block
  @config.id @config.name.downcase.gsub(" ", "_")
  ScriptManager.getInstance.register(ScriptDescriptor.new(@config.id, @config.name, @config.description, @config.version))
end