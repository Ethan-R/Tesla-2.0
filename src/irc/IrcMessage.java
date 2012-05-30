package irc;


public class IrcMessage {
	
	private static final String IDENTIFIER = "@";
	
	private String prefix;
	
	private String command;
	
	private String[] params;
	
	private String trailing;
	
	private void parseInput(String input) {
		int prefixEnd = -1, trailingStart;
		if (input.startsWith(":")) {
			prefixEnd = input.indexOf(" ");
			prefix = input.substring(1, prefixEnd);
		}
		trailingStart = input.indexOf(" :");
		if (trailingStart >= 0) {
			trailing = input.substring(trailingStart+2);
		} else {
			trailingStart = input.length();
		}
		String[] cmdAndParams = input.substring(prefixEnd+1, trailingStart).split(" ");
		command = cmdAndParams[0];
		if (cmdAndParams.length > 1) {
			params = new String[cmdAndParams.length-1];
			System.arraycopy(cmdAndParams, 1, params, 0, params.length);
		}
	}
	
	public IrcMessage(String input) {
		parseInput(input);
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public String getCommand() {
		return command;
	}
	
	public String[] getParams() {
		return params;
	}
	
	public String getTrailing() {
		return trailing;
	}
	
	public String getNick() {
		int index;
		if ((index = prefix.indexOf("!")) == -1) return null;
		return prefix.substring(0, index);
	}
	
	public String getUserCommand() {
		if (!trailing.startsWith(IDENTIFIER)) return null;
		int index = trailing.indexOf(" ");
		if (index == -1)
			index = trailing.length();
		return trailing.substring(1, index);
	}
	
	public String getUserParam() {
		String command = getUserCommand();
		if (command == null || command.length() +2 >= trailing.length())
			return null;
		String param = trailing.substring(command.length()+2);
		return param;
	}
	
}