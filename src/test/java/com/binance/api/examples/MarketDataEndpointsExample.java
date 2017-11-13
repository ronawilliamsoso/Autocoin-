package com.binance.api.examples;

import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.market.AggTrade;
import com.binance.api.client.domain.market.BookTicker;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;
import com.binance.api.client.exception.BinanceApiException;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;

import java.util.List;

/**
 * Examples on how to get market data information such as the latest price of a symbol, etc.
 */
public class MarketDataEndpointsExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiRestClient client = factory.newRestClient();

    // Getting depth of a symbol
    System.out.println(" Getting depth of a symbol-------------------------" );
    OrderBook orderBook = client.getOrderBook("BCCBTC", 10);
    System.out.println(orderBook.getAsks());

 
    System.out.println("Getting latest price of a symbol-------------------------" );
    TickerStatistics tickerStatistics = client.get24HrPriceStatistics("BCCBTC");
    System.out.println(tickerStatistics);

 
    System.out.println("Getting all latest prices-------------------------" );
    List<TickerPrice> allPrices = client.getAllPrices();
    System.out.println(allPrices);

 
    System.out.println("Getting agg trades-------------------------" );
    List<AggTrade> aggTrades = client.getAggTrades("BCCBTC");
    System.out.println(aggTrades);

 
    System.out.println("Weekly candlestick bars for a symbol-------------------------" );
    List<Candlestick> candlesticks = client.getCandlestickBars("BCCBTC", CandlestickInterval.WEEKLY);
    System.out.println(candlesticks);

 
    System.out.println("Getting all book tickers-------------------------" );
    List<BookTicker> allBookTickers = client.getBookTickers();
    System.out.println(allBookTickers);

    // Exception handling
    try {
      client.getOrderBook("UNKNOWN", 10);
    } catch (BinanceApiException e) {
      System.out.println(e.getError().getCode()); // -1121
      System.out.println(e.getError().getMsg());  // Invalid symbol
    }
  }
}
