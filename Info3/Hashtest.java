import java.util.ArrayList;
import java.io.PrintWriter;

public class Hashtest {
	public static void main(String[] args) {
		Em08HashTable<String,String> table1,table2;
		table1 = new Em08HashTable<String,String>(Euro2008.allTeamPlayer.length);
		table2 = new Em08HashTable<String,String>(Euro2008.allTeamPlayer.length);
		boolean one, two;
		System.out.println("Size:" + table1.getSize());

		for (Player p: Euro2008.allTeamPlayer) {
			one = table1.insert(p.getName(),p.getClub());
			two = table2.insert(p.getClub(),p.getName());
			if (!(one || two)) {
				System.err.println("Error on insert "+ p.toString());
				System.exit(1);
			}
		}

		// Query from args to get all players related to one players club
		ArrayList<String> players = getAllPlayers(args[0],table1,table2);
		for (String i : players) {
			System.out.println(i);
		}

		//System.out.println("Table1: \n"+table1);
		//System.out.println("Table2: \n"+table2);

		// writes table1 into a json file
		try{
		    PrintWriter writer = new PrintWriter("table1.json", "UTF-8");
		    writer.println(table1.toJSON());
		    writer.close();
		} catch (Exception e) {
		   // do something
			System.err.println("Could not write table1.json");
		}

		//write table2 into a json file
		try{
		    PrintWriter writer = new PrintWriter("table2.json", "UTF-8");
		    writer.println(table2.toJSON());
		    writer.close();
		} catch (Exception e) {
		   // do something
			System.err.println("Could not write table2.json");
		}
		System.out.println("Test's run without errors");
	}

	public static ArrayList<String> getAllPlayers(String query, Em08HashTable table1, Em08HashTable table2){
		ArrayList<String> club = table1.search(query);
		ArrayList<String> players = null;
		for (String i : club) {
			players = table2.search(i);
		}
		return players;
	}
}