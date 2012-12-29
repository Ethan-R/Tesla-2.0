Tesla-2.0
=========

Improved Version of my previous Tesla IRC bot.

Explanation of python modules and their purpose:

All python modules in jython/scripts/ whose filenames are not prefixed by an underscore are treated as irc event
listeners. This means that they must all be a subclass of InputHandlerAdapter. Modules whose filenames are prefixed
by an underscore do not need to meet that requirement, but they may also not respond to events.
All directories inside of jython/scripts/ are ignored as well (E.g. placing an event listener inside of
jython/scripts/foo would result in nothing happenning).
It is acceptable for event listeners to reference modules whose filenames are prefixed by underscores, and those that
are inside of subdirectories. In fact, that was the whole point of placing those restrictions. Some responses to commands
(such as commands related to games) may take quite a bit of code, and may be better organized by using more than one
file. The previous restrictions were placed to allow such extra files to be created.
