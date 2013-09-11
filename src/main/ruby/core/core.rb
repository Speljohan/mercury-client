require 'java'
require 'jruby'

require_relative 'config_struct'

java_import 'com.mercuryirc.client.command.CommandManager'
java_import 'com.mercuryirc.client.command.RubyCommand'
java_import 'com.mercuryirc.client.script.ScriptManager'
java_import 'com.mercuryirc.client.script.EventManager'
java_import 'com.mercuryirc.client.script.ScriptDescriptor'
java_import 'com.mercuryirc.client.Mercury'
java_import 'com.mercuryirc.model.Message'

def config(&block)
  @config = ConfigStruct.new
  @config.instance_eval &block
  @config.id @config.name.downcase.gsub(" ", "_")
  ScriptManager.getInstance.register(ScriptDescriptor.new(@config.id, @config.name, @config.description, @config.version))
end

def on(what, &block)
  EventManager.getInstance.register(@config.id, what, false, &block)
end

def register_command(cmd, &block)
  CommandManager.getInstance().register RubyCommand.new("#{cmd.to_s}", &block)
end

def current_connection
  Mercury.appPane.getTabPane.getSelected.getConnection
end

def send_command(cmd, msg)
  current_connection.process(Mercury.appPane.getTabPane.getSelected.getEntity, "/#{cmd} #{msg}")
end

def send_message(msg)
  current_connection.process(Mercury.appPane.getTabPane.getSelected.getEntity, "#{msg}")
end