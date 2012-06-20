package com.trs.smas.tracking.util;

import com.trs.dev4.jdk16.utils.StringHelper;

public class PageUtil {
	
	/**
	 * 分页显示的指定页数显示. <BR>
	 * 等价于
	 * <code>generatePageTableBottom(link, pageSize, totalItem, currentPage, "pageNo")</code>
	 * 
	 * @param link
	 *            页面链接
	 * @param pageSize
	 *            每页的条数
	 * @param totalItem
	 *            总条数
	 * @param currentPage
	 *            当前页的序号. 0表示第1页, -1表示不分页.
	 * @return 分页的字符串Buffer
	 */
	public static StringBuffer generatePageTableBottom(String link, int pageSize, int totalItem, int currentPage) {
		return generatePageTableBottom(link, pageSize, totalItem, currentPage, "pageNo", totalItem);
	}

	/**
	 * 分页显示的指定页数显示. <BR>
	 * 等价于
	 * <code>generatePageTableBottom(link, pageSize, totalItem, currentPage, "pageNo")</code>
	 * 
	 * @param link
	 *            页面链接
	 * @param pageSize
	 *            每页的条数
	 * @param totalItem
	 *            总条数
	 * @param currentPage
	 *            当前页的序号. 0表示第1页, -1表示不分页.
	 * @return 分页的字符串Buffer
	 */
	public static StringBuffer generatePageTableBottom(String link, int pageSize, int totalItem, int currentPage, long noRepeatCount) {
		return generatePageTableBottom(link, pageSize, totalItem, currentPage, "pageNo", noRepeatCount);
	}

	/**
	 * 分页显示的指定页数显示.
	 * 
	 * @param link
	 *            页面链接
	 * @param pageSize
	 *            每页的条数
	 * @param totalItem
	 *            总条数
	 * @param currentPage
	 *            当前页的序号. 0表示第1页, -1表示不分页.
	 * @param pageParamName
	 *            request中表示当前页序号的参数名.
	 * @return 分页的html字符串Buffer
	 */
	private static StringBuffer generatePageTableBottom(String link, int pageSize, int totalItem, int currentPage, String pageParamName, long totalCount) {
		link = StringHelper.getURLSafe(link);

		final String rowBegin = "<TR><TD class=navigation_page_td vAlign=top>";
		final String rowEnd = "</TD></TR>";
		if (totalItem <= 0) {
			return new StringBuffer(rowBegin).append("没有记录!").append(rowEnd);
		}
		if (currentPage == -1) {
			return new StringBuffer(rowBegin).append("<SPAN class=navigation_channel>共").append(totalItem).append("条记录</SPAN>").append(rowEnd);
		}
		int pages = totalItem / pageSize; // 总页数
		if (totalItem % pageSize > 0) {
			pages++;
		}
//		if (LOG.isDebugEnabled()) {
//			LOG.debug("总数=" + totalItem + ", 每页: " + pageSize + "总页数=" + pages + ", 当前页: " + currentPage);
//		}
		if (currentPage < -1 || currentPage > pages) {
			return new StringBuffer(rowBegin).append("无效页码!").append(rowEnd);
		}

		StringBuffer htmlCode = new StringBuffer(512);
		htmlCode.append(rowBegin);

		// 多于10页显示导航.
		if (pages > 10) {
			naviBar(link, currentPage, pages, htmlCode, pageParamName);
		}

		// 多于1页, 则显示首页/上页/下页/末页
		if (pages >= 1) {
			pageBar(link, currentPage, pages, htmlCode, pageParamName);
		}

		// 当前页/总页数/条数说明
		if (totalItem == totalCount) {
			htmlCode.append("<SPAN class=navigation_channel>(第").append(currentPage + 1).append("页, 共").append(pages).append("页/").append(totalItem)
					.append("条记录)</SPAN>");
		} else {
			htmlCode.append("<SPAN class=navigation_channel>(第").append(currentPage + 1).append("页, 共").append(pages).append("页/排重后").append(totalItem)
					.append("条，总共").append(totalCount).append("条)").append("</SPAN>");
		}
		htmlCode.append(rowEnd);
		return htmlCode;
	}

	public static StringBuffer generatePageTableBottomPageSize(String link, int pageSize, int totalItem, int currentPage, String pageParamName) {
		if (pageSize <= 0) {
			pageSize = 15;
		}

		link = StringHelper.getURLSafe(link);

		final String rowBegin = "<TR><TD class=navigation_page_td vAlign=top>";
		final String rowEnd = "</TD></TR>";
		if (totalItem <= 0) {
			return new StringBuffer(rowBegin).append("没有记录!").append(rowEnd);
		}
		if (currentPage == -1) {
			return new StringBuffer(rowBegin).append("<SPAN class=navigation_channel>共").append(totalItem).append("条记录</SPAN>").append(rowEnd);
		}
		int pages = totalItem / pageSize; // 总页数
		if (totalItem % pageSize > 0) {
			pages++;
		}
//		if (LOG.isDebugEnabled()) {
//			LOG.debug("总数=" + totalItem + ", 每页: " + pageSize + "总页数=" + pages + ", 当前页: " + currentPage);
//		}
		if (currentPage < -1 || currentPage > pages) {
			return new StringBuffer(rowBegin).append("无效页码!").append(rowEnd);
		}

		StringBuffer htmlCode = new StringBuffer(512);
		htmlCode.append(rowBegin);

		// 多于10页显示导航.
		if (pages > 10) {
			naviBarWithPageSize(link, currentPage, pages, htmlCode, pageParamName, pageSize);
		}

		// 多于1页, 则显示首页/上页/下页/末页
		if (pages >= 1) {
			pageBarWithPageSize(link, currentPage, pages, htmlCode, pageParamName, pageSize);
		}

		String href = link;
		if (href.indexOf('?') > 0) {
			href = href + "&pageSize=";
		} else {
			href = href + "?pageSize=";
		}

		// 当前页/总页数/条数说明
		htmlCode.append("<SPAN class=navigation_channel>");
		htmlCode.append("(第" + (currentPage + 1));
		htmlCode.append("页, 共");
		htmlCode.append(pages).append("页/");
		htmlCode.append(totalItem).append("条记录)");
		htmlCode.append(", ");
		htmlCode.append("每页显示");
		htmlCode.append("<input type='text' id='pageSize' name='pageSize' value='" + pageSize + "' style='width:30px' >");
		htmlCode.append("条");

		htmlCode.append("<input type='button' name='go' value='GO!'  onclick=\"javascript:var pageSize=document.getElementById('pageSize').value;document.location.href='"
				+ href + "' + pageSize ;\" >");
		htmlCode.append("</SPAN>");

		htmlCode.append(rowEnd);

		return htmlCode;
	}

	// 多于1页时, 显示首页/上页/下页/末页. (先决条件: pages > 1)
	private static void pageBar(String link, int currentPage, int totalPages, StringBuffer htmlCode, String pageParamName) {
		int prePage = (currentPage == 0) ? 0 : currentPage - 1;
		int postPage = (currentPage == totalPages - 1) ? totalPages - 1 : currentPage + 1;
		StringBuffer sbPageParam = new StringBuffer(10);
		sbPageParam.append(link.indexOf('?') > 0 ? '&' : '?').append(pageParamName).append('=');

		if (currentPage != 0) {
			htmlCode.append("<A class=navigation_page_link href='").append(link).append(sbPageParam).append("0'>首页</A> "); // 首页
			htmlCode.append("<A class=navigation_page_link href='").append(link).append(sbPageParam).append(prePage).append("'>上页</A> "); // 上页
		}

		if (currentPage != totalPages - 1 && currentPage < totalPages) {
			htmlCode.append("<A class=navigation_page_link href='").append(link).append(sbPageParam).append(postPage).append("'>下页</A> "); // 下页
			htmlCode.append("<A class=navigation_page_link href='").append(link).append(sbPageParam).append(totalPages - 1).append("'>末页</A>"); // 末页
		}

	}

	// 多于10条记录时, 显示一个10条的导航. (先决条件: pages > 10)
	private static void naviBar(String link, int currentPage, int totalPages, StringBuffer htmlCode, String pageParamName) {
		int naviSize = 10; // 导航条数为10.
		int naviBase = currentPage / naviSize;
		int naviTop = (naviBase + 1) * naviSize;
		int preNavi = (naviBase <= 0) ? 0 : naviBase * naviSize - 1;
		int postNavi = (naviTop >= totalPages - 1) ? totalPages - 1 : naviTop;
		StringBuffer sbPageParam = new StringBuffer(10);
		sbPageParam.append(link.indexOf('?') > 0 ? '&' : '?').append(pageParamName).append('=');
		// 是否显示前10页
		if (naviBase > 0) {
			htmlCode.append("<A class=navigation_page_link href='").append(link).append(sbPageParam).append(preNavi).append("'>&lt;前10页&gt;</A> ");
		}
		for (int i = naviBase * naviSize; i < naviTop; i++) {
			if (i >= totalPages) {
				break;
			}
			// 计算从0开始, 显示则从1开始, 故显示页码时要给i加1
			if (i == currentPage) {
				htmlCode.append("&nbsp;").append(i + 1).append("&nbsp;");
			} else {
				htmlCode.append(" <A class=navigation_page_link href='").append(link).append(sbPageParam).append(i).append("'>").append(i + 1).append("</A> ");
			}
		}
		// 是否显示后N页
		int actualMaxPageNumber = totalPages - 1; // 从0开始的最大页数
		if (naviBase < (totalPages / naviSize) && currentPage != actualMaxPageNumber) {
			htmlCode.append(" <A class=navigation_page_link href='").append(link).append(sbPageParam).append(postNavi).append("'>&lt;后")
					.append((totalPages - currentPage <= naviSize) ? totalPages - currentPage - 1 : naviSize).append("页&gt;</A> ");
		}
	}

	// 多于1页时, 显示首页/上页/下页/末页. (先决条件: pages > 1)
	private static void pageBarWithPageSize(String link, int currentPage, int totalPages, StringBuffer htmlCode, String pageParamName, int pageSize) {
		int prePage = (currentPage == 0) ? 0 : currentPage - 1;
		int postPage = (currentPage == totalPages - 1) ? totalPages - 1 : currentPage + 1;
		StringBuffer sbPageParam = new StringBuffer(10);
		sbPageParam.append(link.indexOf('?') > 0 ? '&' : '?').append(pageParamName).append('=');

		if (currentPage != 0) {
			htmlCode.append("<A class=navigation_page_link href='").append(link).append(sbPageParam).append("0").append("&pageSize=").append(pageSize)
					.append("'>首页</A> "); // 首页
			htmlCode.append("<A class=navigation_page_link href='").append(link).append(sbPageParam).append(prePage).append("&pageSize=").append(pageSize)
					.append("'>上页</A> "); // 上页
		}

		if (currentPage != totalPages - 1 && currentPage < totalPages) {
			htmlCode.append("<A class=navigation_page_link href='").append(link).append(sbPageParam).append(postPage).append("&pageSize=").append(pageSize)
					.append("'>下页</A> "); // 下页
			htmlCode.append("<A class=navigation_page_link href='").append(link).append(sbPageParam).append(totalPages - 1).append("&pageSize=")
					.append(pageSize).append("'>末页</A>"); // 末页
		}

	}

	private static void naviBarWithPageSize(String link, int currentPage, int totalPages, StringBuffer htmlCode, String pageParamName, int pageSize) {
		int naviSize = 10; // 导航条数为10.
		int naviBase = currentPage / naviSize;
		int naviTop = (naviBase + 1) * naviSize;
		int preNavi = (naviBase <= 0) ? 0 : naviBase * naviSize - 1;
		int postNavi = (naviTop >= totalPages - 1) ? totalPages - 1 : naviTop;
		StringBuffer sbPageParam = new StringBuffer(10);
		sbPageParam.append(link.indexOf('?') > 0 ? '&' : '?').append(pageParamName).append('=');
		// 是否显示前10页
		if (naviBase > 0) {
			htmlCode.append("<A class=navigation_page_link href='").append(link).append(sbPageParam).append(preNavi).append("&pageSize=").append(pageSize)
					.append("'>&lt;前10页&gt;</A> ");
		}
		for (int i = naviBase * naviSize; i < naviTop; i++) {
			if (i >= totalPages) {
				break;
			}
			// 计算从0开始, 显示则从1开始, 故显示页码时要给i加1
			if (i == currentPage) {
				htmlCode.append("&nbsp;").append(i + 1).append("&nbsp;");
			} else {
				htmlCode.append(" <A class=navigation_page_link href='").append(link).append(sbPageParam).append(i).append("&pageSize=").append(pageSize)
						.append("'>").append(i + 1).append("</A> ");
			}
		}

		// 是否显示后N页
		int actualMaxPageNumber = totalPages - 1; // 从0开始的最大页数
		if (naviBase < (totalPages / naviSize) && currentPage != actualMaxPageNumber) {
			htmlCode.append(" <A class=navigation_page_link href='").append(link).append(sbPageParam).append(postNavi).append("&pageSize=").append(pageSize)
					.append("'>&lt;后").append((totalPages - currentPage <= naviSize) ? totalPages - currentPage - 1 : naviSize).append("页&gt;</A> ");
		}
	}

	/**
	 * 分页显示的指定页数显示. <BR>
	 * 
	 * @param 分页要执行的Script函数
	 * @param pageSize
	 *            每页的条数
	 * @param totalItem
	 *            总条数
	 * @param currentPage
	 *            当前页的序号. 0表示第1页, -1表示不分页.
	 * @return 分页的字符串Buffer
	 */
	public static StringBuffer pageTableBottomWithScript(String function, int pageSize, int totalItem, int currentPage) {
		return pageTableBottomWithScript(function, pageSize, totalItem, currentPage, totalItem);
	}

	private static StringBuffer pageTableBottomWithScript(String function, int pageSize, int totalItem, int currentPage, long totalCount) {
		final String rowBegin = "<TR><TD class=navigation_page_td vAlign=top>";
		final String rowEnd = "</TD></TR>";
		if (totalItem <= 0) {
			return new StringBuffer(rowBegin).append("没有记录!").append(rowEnd);
		}
		if (currentPage == -1) {
			return new StringBuffer(rowBegin).append("<SPAN class=navigation_channel>共").append(totalItem).append("条记录</SPAN>").append(rowEnd);
		}
		int pages = totalItem / pageSize; // 总页数
		if (totalItem % pageSize > 0) {
			pages++;
		}
//		if (LOG.isDebugEnabled()) {
//			LOG.debug("总数=" + totalItem + ", 每页: " + pageSize + "总页数=" + pages + ", 当前页: " + currentPage);
//		}
		if (currentPage < -1 || currentPage > pages) {
			return new StringBuffer(rowBegin).append("无效页码!").append(rowEnd);
		}

		StringBuffer htmlCode = new StringBuffer(512);
		htmlCode.append(rowBegin);

		// 多于10页显示导航.
		if (pages > 10) {
			naviBarWithScript(function, currentPage, pages, htmlCode);
		}

		// 多于1页, 则显示首页/上页/下页/末页
		if (pages >= 1) {
			pageBarWithScript(function, currentPage, pages, htmlCode);
		}

		// 当前页/总页数/条数说明
		if (totalItem == totalCount) {
			htmlCode.append("<SPAN class=navigation_channel>(第").append(currentPage + 1).append("页, 共").append(pages).append("页/").append(totalItem)
					.append("条记录)</SPAN>");
		} else {
			htmlCode.append("<SPAN class=navigation_channel>(第").append(currentPage + 1).append("页, 共").append(pages).append("页/排重后").append(totalItem)
					.append("条，总共").append(totalCount).append("条)").append("</SPAN>");
		}
		htmlCode.append(rowEnd);
		return htmlCode;
	}

	// 多于1页时, 显示首页/上页/下页/末页. (先决条件: pages > 1)
	private static void pageBarWithScript(String function, int currentPage, int totalPages, StringBuffer htmlCode) {
		int prePage = (currentPage == 0) ? 0 : currentPage - 1;
		int postPage = (currentPage == totalPages - 1) ? totalPages - 1 : currentPage + 1;
		if (currentPage != 0) {
			htmlCode.append("<A class=navigation_page_link href='").append("javascript:").append(function + "(" + 0 + ")").append("'>首页</A> "); // 首页
			htmlCode.append("<A class=navigation_page_link href='").append("javascript:").append(function + "(" + prePage + ")").append("'>上页</A> "); // 上页
		}

		if (currentPage != totalPages - 1 && currentPage < totalPages) {
			htmlCode.append("<A class=navigation_page_link href='").append("javascript:").append(function + "(" + postPage + ")").append("'>下页</A> "); // 下页
			htmlCode.append("<A class=navigation_page_link href='").append("javascript:").append(function + "(" + (totalPages - 1) + ")").append("'>末页</A>"); // 末页
		}

	}

	// 多于10条记录时, 显示一个10条的导航. (先决条件: pages > 10)
	private static void naviBarWithScript(String function, int currentPage, int totalPages, StringBuffer htmlCode) {
		int naviSize = 10; // 导航条数为10.
		int naviBase = currentPage / naviSize;
		int naviTop = (naviBase + 1) * naviSize;
		int preNavi = (naviBase <= 0) ? 0 : naviBase * naviSize - 1;
		int postNavi = (naviTop >= totalPages - 1) ? totalPages - 1 : naviTop;
		// 是否显示前10页
		if (naviBase > 0) {
			htmlCode.append("<A class=navigation_page_link href='").append("javascript:").append(function + "(" + preNavi + ")").append("'>&lt;前10页&gt;</A> ");
		}
		for (int i = naviBase * naviSize; i < naviTop; i++) {
			if (i >= totalPages) {
				break;
			}
			// 计算从0开始, 显示则从1开始, 故显示页码时要给i加1
			if (i == currentPage) {
				htmlCode.append("&nbsp;").append(i + 1).append("&nbsp;");
			} else {
				htmlCode.append(" <A class=navigation_page_link href='").append("javascript:").append(function + "(" + i + ")").append("'>").append(i + 1)
						.append("</A> ");
			}
		}
		// 是否显示后N页
		int actualMaxPageNumber = totalPages - 1; // 从0开始的最大页数
		if (naviBase < (totalPages / naviSize) && currentPage != actualMaxPageNumber) {
			htmlCode.append(" <A class=navigation_page_link href='").append("javascript:").append(function + "(" + postNavi + ")").append("'>&lt;后")
					.append((totalPages - currentPage <= naviSize) ? totalPages - currentPage - 1 : naviSize).append("页&gt;</A> ");
		}
	}
}
