package practice;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
public class StreamMain {
	public static void main(String[] args) {
		
		List<Fruit> list = new ArrayList<Fruit>();
		list.add(new Fruit ("Mango", 58, 100, "Yellow"));
		list.add(new Fruit("Guava", 67, 80, "DarkGreen"));
		list.add(new Fruit("Apple", 500, 95, "Red"));
		list.add(new Fruit("Grapes", 99, 100, "Black"));
		list.add(new Fruit("Cherry", 49, 200, "Red"));
		
		
		System.out.println("________________________________________________________");
		System.out.println("Ques. 1");
		System.out.println("________________________________________________________\n");
		System.out.println("fruit names of low calories fruits i.e. calories < 100 sorted in descending order of calories: \n");

		list.stream().filter(fruit -> fruit.getCalories() < 100)
				.sorted(Comparator.comparing(Fruit::getCalories).reversed())
				.forEach(System.out::println);

		System.out.println("-------------------------------------------");

		System.out.println("\nThis is to get colour in order wise:\n");
		list.stream().sorted(Comparator.comparing(Fruit::getColor))
				.forEach(System.out::println);
		System.out.println("-------------------------------------------");

		System.out
				.println("\nonly RED color fruits sorted as per their price in ascending order: \n");
		// THIS IS ALTERNATIVE
		// list.stream().filter(el->ele.getColor().startsWith("R")).sorted(Comparator.comparing(Fruit::getPrice)).forEach(System.out::println);
		list.stream().filter(ele -> ele.getColor() == "Red")
				.sorted(Comparator.comparing(Fruit::getPrice))
				.map(f -> f.getName() + " : " + f.getColor())
				.forEach(System.out::println);
		
		System.out.println("________________________________________________________");
		System.out.println("Ques. 2");
		System.out.println("________________________________________________________\n");
		List<News> news = new ArrayList<News>();
		news.add(new News(120, "Arpita", "Sneha", "Have a nice day"));
		news.add(new News(210, "Rashmi", "ashu","see you later, make a good budgets"));
		news.add(new News(120, "Arpita", "abhi", "Read novels with good budget"));
		news.add(new News(122, "chaya", "Sneha", "nice child"));
		news.add(new News(120, "Arpita", "suma", "Read books"));

		//System.out.println("\n" + news);

		System.out.println("No of times 'budget' used in comments is: ");
		System.out.println(news.stream()
				.filter(x -> x.getComment().contains("budget")).count());

		System.out.println("---------------------------------------------------------------");

		// 6 question max commented user
		System.out.println("user has posted maximum comments: ");
		System.out
				.println(news.stream()
						.max(Comparator.comparing(News::getCommentByUser))
						.get().getCommentByUser());
		System.out.println("---------------------------------------------------------------");


		// /Display commentByUser wise number of comments
		Map<String, Long> var = (news.stream().map(v -> v.getCommentByUser()).collect(Collectors.groupingBy(Function.identity(),
				Collectors.counting())));

				
		System.out.println("Display commentByUser wise number of comments:\n "
				+ var);
		System.out.println("---------------------------------------------------------------");


		ArrayList<Integer> newsfind = (ArrayList<Integer>) news.stream()
				.map(index -> index.getNewsId()).collect(Collectors.toList());
		Map<Integer, Long> mp = newsfind.stream().collect(
				Collectors.groupingBy(Function.identity(),
						Collectors.counting()));
		System.out.println(mp);

		long maxvalue = 0;
		int key = 0;
		for (Map.Entry<Integer, Long> Entry : mp.entrySet()) {
			if (Entry.getValue() > maxvalue) {
				maxvalue = Entry.getValue();
				key = Entry.getKey();
			}
		}
		System.out.println("the newsId which has received maximum comments : "
				+ key);
		System.out.println("The max times theperson commented is: " + maxvalue
				+ " The maxtimes commented  person's newsId is: " + key);

		
		
		System.out.println("________________________________________________________");
		System.out.println("Ques. 3");
		System.out.println("________________________________________________________\n");
		List<Trader> trader = new ArrayList<Trader>();
		trader.add(new Trader("Priya", "Telangana"));
		trader.add(new Trader("Rohini", "Kolkata"));
		trader.add(new Trader("Chiya", "Banaras"));
		trader.add(new Trader("Deepika", "Punjab"));
		trader.add(new Trader("Urvi", "Pune"));
		trader.add(new Trader("Akshita", "Pune"));
		trader.add(new Trader("Deepika", "Pune"));

		System.out.println("All the unique cities: ");
		trader.stream().map(t -> t.getCity()).distinct()
				.forEach(System.out::println);

		System.out.println("-------------------------------------------");

		System.out.println("All traders from Pune and sort them by name");
		trader.stream().filter(t -> t.getCity() == "Pune")
				.sorted(Comparator.comparing(Trader::getName))
				.forEach(System.out::println);

		System.out.println("-------------------------------------");

		System.out.println("All traders names sorted alphabetically");
		trader.stream().sorted(Comparator.comparing(Trader::getName))
				.map(t -> t.getName()).distinct().forEach(System.out::println);

		System.out.println("--------------------");
		boolean exists = trader.stream().anyMatch(
				t -> t.getCity().equals("Indore"));
		System.out.println("any traders based in Indore: " + exists);
		
		System.out.println("________________________________________________________");
		System.out.println("Ques. 4");
		System.out.println("________________________________________________________\n");
			List<Transaction> trans = new ArrayList<Transaction>();
			Trader t1 = new Trader("abhi", "Delhi");
			trans.add(new Transaction(t1, 1996, 350));

			Trader t2 = new Trader("shri", "banglore");
			trans.add(new Transaction(t2, 2011, 250));

			Trader t3 = new Trader("mallika", "Delhi");
			trans.add(new Transaction(t3, 2005, 150));

			Trader t4 = new Trader("Amuda", "chennai");
			trans.add(new Transaction(t4, 2010, 750));
			
			Trader t5 = new Trader("Akshat", "Rajasthan");
			trans.add(new Transaction(t5, 2011, 800));

			Trader t6 = new Trader("Amrinder", "Punjab");
			trans.add(new Transaction(t6, 2011, 900));
			
			System.out.println("All transactions in the year 2011 and sort them by value");
			trans.stream().filter(t -> t.getYear() == 2011)
			.sorted(Comparator.comparing(Transaction::getValue))
			.map(t -> " Name: " + t.getTrader().getName() +" City : " + t.getTrader().getCity() +
					"  Value: " + t.getValue())
			.forEach(System.out::println);

			System.out.println("\n"+"All transactions values from the traders living in Delhi.");
			trans.stream()
					.filter(t -> t.getTrader().getCity() == "Delhi")
					.map(t -> " Name: " + t.getTrader().getName() + " :: Year: " + t.getYear()
							+ " value: " + t.getValue())
					.forEach(System.out::println);

			Transaction maxv = trans.stream()
					.max(Comparator.comparing(Transaction::getValue)).get();
			System.out.println("\nThe highest value of all the transactions: \n" + maxv);

			Transaction minv = trans.stream()
					.min(Comparator.comparing(Transaction::getValue)).get();
			System.out.println("\nThe transaction with the smallest value.: \n" + minv);

			
		}
	}
