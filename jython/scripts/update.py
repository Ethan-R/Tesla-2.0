import echo
import reverse
import ircinput
import cmd
import calc

from irc.adaptors import InputHandlerAdapter

class update(InputHandlerAdapter):
    
    def handleUserCommand(self, ircEvent):
        msg = ircEvent.getSource()
        if (msg.getUserCommand() == "update"):
            try:
                self.getIrcClient().getInputEventListeners().clear()
                reload(echo)
                reload(reverse)
                reload(ircinput)
                reload(cmd)
                reload(calc)
                self.getIrcClient().loadModules("jython/scripts/", "scripts")
                self.getIrcClient().privMsg("Scripts updated.", msg.getParams()[0])
            except BaseException, e:
                print e
                self.getIrcClient().privMsg(str(e), msg.getParams()[0])