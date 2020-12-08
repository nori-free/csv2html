package primeministers;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.awt.image.BufferedImage;
import libraries.Condition;
import libraries.ImageUtil;

public class Translator {

	private Table inputTable;

	private Table outputTable;

	public Translator(Object aClassAttribute) {
		Attributes.flushBaseDirectory();
		this.inputTable = new Table(aClassAttribute, new Attributes(aClassAttribute, "input"));
		this.outputTable = new Table(aClassAttribute, new Attributes(aClassAttribute, "output"));
	}

	/**
	* カレンダーを比較して差分を取得
	*/
	private int compareTo(Calendar aCalendar, Calendar anotherCalendar) {
		long differenceTime = aCalendar.getTimeInMillis() - anotherCalendar.getTimeInMillis();
		int millisOfDay = 86400000;
		return Math.abs((int)(differenceTime / millisOfDay)) + 1;
	}

	public Tuple computeNumberOfDays(Tuple aTuple) {
		String aDates = aTuple.values().get(this.inputTable.attributes().indexOfPeriod());
		String[] splittedDates = aDates.split("〜");
		String stringOfStartDate = splittedDates[0];
		Calendar startDate = this.getCalendar(splittedDates[0]);
		new Condition(() -> splittedDates.length >= 2)
		.ifThenElse(() -> {
			Calendar endDate = this.getCalendar(splittedDates[1]);
			aTuple.add(this.inputTable.attributes().indexOfPeriod() + 1, String.valueOf(this.compareTo(startDate, endDate)));
		},
		() -> {
			Calendar endDate = this.getCalendar();
			aTuple.add(this.inputTable.attributes().indexOfPeriod() + 1, String.valueOf(this.compareTo(startDate, endDate)));
		});

		return aTuple;
	}

	public void execute() {
		Downloader aDownloader = new Downloader(this.inputTable);
		List<Tuple> tuples = aDownloader.perform();
		Writer aWriter = new Writer(this.outputTable);
		AtomicInteger anAtomicInteger = new AtomicInteger();

		// ヘッダの情報をタプルに詰める
		List<String> header = new ArrayList<String>();
		this.outputTable.attributes().getEnum().forEach(header::add);
		System.out.println(header);
		tuples.add(0, new Tuple(header));


		tuples.forEach(aTuple -> {
			int index = anAtomicInteger.getAndIncrement();
			// HTMLタグをつける
			List<String> formattedList = new ArrayList<String>();
			Map<String, String> aMap = new HashMap<String, String>();

			new Condition(() -> index == 0)
			.ifThenElse(
				() -> {
					// Headerの処理
					aTuple.values().forEach(aColumn -> {
						formattedList.add(aWriter.tableHeader(aColumn));
					});
				},
				() -> {
					// Bodyの処理
					Tuple anotherTuple = this.computeNumberOfDays(aTuple);
					anotherTuple.values().forEach(aColumn -> {
						new Condition(() -> ImageUtil.isImageFile(aColumn))
						.ifThenElse(() -> {
							// imagefile.
							new Condition(() -> aColumn.startsWith("thumbnails"))
							.ifTrue(() -> {
								// thumbnails
								aMap.put("thumbnails", aColumn);
							});
							new Condition(() -> aColumn.startsWith("images"))
							.ifTrue(() -> {
								// images
								aMap.put("images", aColumn);
							});
							new Condition(() -> aMap.get("thumbnails") != null && aMap.get("images") != null)
							.ifTrue(() -> {
								// 画像をHTMLに充てる
								new Condition(() -> index % 2 == 0)
								.ifThenElse(() -> {
									// even.
									formattedList.add(aWriter.getImageWithYellow(aMap));
								},
								() -> {
									// odd.
									formattedList.add(aWriter.getImageWithBlue(aMap));
								});
							});
						},
						() -> {
							// not imagefile.
							// is anAtomicInteger even.
							new Condition(() -> index % 2 == 0)
							.ifThenElse(() -> {
								// even.
								formattedList.add(aWriter.getTableDataWithYellow(aColumn));
							},
							() -> {
								// odd.
								formattedList.add(aWriter.getTableDataWithBlue(aColumn));
							});
						});
					});
				});
			this.outputTable.add(new Tuple(formattedList));
		});
		aWriter.write(this.outputTable);
	}

	private Calendar getCalendar() {
		Calendar instanceOfCalendar = Calendar.getInstance(Locale.JAPAN);
		String thisYear = Integer.toString(instanceOfCalendar.get(Calendar.YEAR)).concat("年");
		String thisMonth = Integer.toString(instanceOfCalendar.get(Calendar.MONTH) + 1).concat("月");
		String thisDate = Integer.toString(instanceOfCalendar.get(Calendar.DATE)).concat("日");
		String stringOfEndDate = thisYear.concat(thisMonth.concat(thisDate));
		return this.getCalendar(stringOfEndDate);
	}

	private Calendar getCalendar(String aString) {
		Calendar aCalendar = Calendar.getInstance();
		try {
			String[] firstSplit = aString.split("年");
			String[] secondSplit = firstSplit[1].split("月");
			String[] thirdSplit = secondSplit[1].split("日");
			int year = 1990;
			int month = 1;
			int day = 1;
			year = Integer.valueOf(firstSplit[0]);
			month = Integer.valueOf(secondSplit[0]) - 1;
			day = Integer.valueOf(thirdSplit[0]);
			aCalendar.set(year, month, day);
		} catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
			e.printStackTrace();
		}
		return aCalendar;
	}

}
