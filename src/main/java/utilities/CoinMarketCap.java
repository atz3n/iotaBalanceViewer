package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/**
 * Copyright (c) 2017 Miguel Fuentes Buchholtz. http://variacode.com
 * @author miguel@variacode.com
 */
public class CoinMarketCap {

    private final boolean throttle;

    public CoinMarketCap() {
        throttle = true;
    }

    public CoinMarketCap(boolean throttle) {
        this.throttle = throttle;
    }

    RateLimiter rateLimiter = RateLimiter.create(0.033333);

    private final Gson gson = new Gson();

    public enum CurrencyConvert {
        AUD, BRL, CAD, CHF, CNY, EUR, GBP, HKD, IDR, INR, JPY, KRW, MXN, RUB
    }

    public class Ticker {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("symbol")
        @Expose
        private String symbol;
        @SerializedName("rank")
        @Expose
        private Integer rank;
        @SerializedName("price_usd")
        @Expose
        private BigDecimal priceUsd;
        @SerializedName("price_aud")
        @Expose
        private BigDecimal priceAud;
        @SerializedName("price_brl")
        @Expose
        private BigDecimal priceBrl;
        @SerializedName("price_cad")
        @Expose
        private BigDecimal priceCad;
        @SerializedName("price_chf")
        @Expose
        private BigDecimal priceChf;
        @SerializedName("price_clp")
        @Expose
        private BigDecimal priceClp;
        @SerializedName("price_cny")
        @Expose
        private BigDecimal priceCny;
        @SerializedName("price_czk")
        @Expose
        private BigDecimal priceCzk;
        @SerializedName("price_dkk")
        @Expose
        private BigDecimal priceDkk;
        @SerializedName("price_eur")
        @Expose
        private BigDecimal priceEur;
        @SerializedName("price_gbp")
        @Expose
        private BigDecimal priceGbp;
        @SerializedName("price_hkd")
        @Expose
        private BigDecimal priceHkd;
        @SerializedName("price_huf")
        @Expose
        private BigDecimal priceHuf;
        @SerializedName("price_idr")
        @Expose
        private BigDecimal priceIdr;
        @SerializedName("price_ils")
        @Expose
        private BigDecimal priceIls;
        @SerializedName("price_Inr")
        @Expose
        private BigDecimal priceInr;
        @SerializedName("price_jpy")
        @Expose
        private BigDecimal priceJpy;
        @SerializedName("price_krw")
        @Expose
        private BigDecimal priceKrw;
        @SerializedName("price_mxn")
        @Expose
        private BigDecimal priceMxn;
        @SerializedName("price_myr")
        @Expose
        private BigDecimal priceMyr;
        @SerializedName("price_nok")
        @Expose
        private BigDecimal priceNok;
        @SerializedName("price_nzd")
        @Expose
        private BigDecimal priceNzd;
        @SerializedName("price_php")
        @Expose
        private BigDecimal pricePhp;
        @SerializedName("price_pkr")
        @Expose
        private BigDecimal pricePkr;
        @SerializedName("price_pln")
        @Expose
        private BigDecimal pricePln;
        @SerializedName("price_rub")
        @Expose
        private BigDecimal priceRub;
        @SerializedName("price_sek")
        @Expose
        private BigDecimal priceSek;
        @SerializedName("price_sgd")
        @Expose
        private BigDecimal priceSgd;
        @SerializedName("price_thb")
        @Expose
        private BigDecimal priceThb;
        @SerializedName("price_try")
        @Expose
        private BigDecimal priceTry;
        @SerializedName("price_twd")
        @Expose
        private BigDecimal priceTwd;
        @SerializedName("price_zar")
        @Expose
        private BigDecimal priceZar;
        @SerializedName("price_btc")
        @Expose
        private BigDecimal priceBtc;
        @SerializedName("24h_volume_usd")
        @Expose
        private BigDecimal _24hVolumeUsd;
        @SerializedName("market_cap_usd")
        @Expose
        private BigDecimal marketCapUsd;
        @SerializedName("available_supply")
        @Expose
        private BigDecimal availableSupply;
        @SerializedName("total_supply")
        @Expose
        private BigDecimal totalSupply;
        @SerializedName("percent_change_1h")
        @Expose
        private BigDecimal percentChange1h;
        @SerializedName("percent_change_24h")
        @Expose
        private BigDecimal percentChange24h;
        @SerializedName("percent_change_7d")
        @Expose
        private BigDecimal percentChange7d;
        @SerializedName("last_updated")
        @Expose
        private Long lastUpdated;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public Integer getRank() {
            return rank;
        }

        public void setRank(Integer rank) {
            this.rank = rank;
        }

        public BigDecimal getPriceUsd() {
            return priceUsd;
        }

        public void setPriceUsd(BigDecimal priceUsd) {
            this.priceUsd = priceUsd;
        }

        public BigDecimal getPriceBtc() {
            return priceBtc;
        }

        public void setPriceBtc(BigDecimal priceBtc) {
            this.priceBtc = priceBtc;
        }

        public BigDecimal get24hVolumeUsd() {
            return _24hVolumeUsd;
        }

        public void set24hVolumeUsd(BigDecimal _24hVolumeUsd) {
            this._24hVolumeUsd = _24hVolumeUsd;
        }

        public BigDecimal getMarketCapUsd() {
            return marketCapUsd;
        }

        public void setMarketCapUsd(BigDecimal marketCapUsd) {
            this.marketCapUsd = marketCapUsd;
        }

        public BigDecimal getAvailableSupply() {
            return availableSupply;
        }

        public void setAvailableSupply(BigDecimal availableSupply) {
            this.availableSupply = availableSupply;
        }

        public BigDecimal getTotalSupply() {
            return totalSupply;
        }

        public void setTotalSupply(BigDecimal totalSupply) {
            this.totalSupply = totalSupply;
        }

        public BigDecimal getPercentChange1h() {
            return percentChange1h;
        }

        public void setPercentChange1h(BigDecimal percentChange1h) {
            this.percentChange1h = percentChange1h;
        }

        public BigDecimal getPercentChange24h() {
            return percentChange24h;
        }

        public void setPercentChange24h(BigDecimal percentChange24h) {
            this.percentChange24h = percentChange24h;
        }

        public BigDecimal getPercentChange7d() {
            return percentChange7d;
        }

        public void setPercentChange7d(BigDecimal percentChange7d) {
            this.percentChange7d = percentChange7d;
        }

        public Long getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(Long lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

		public BigDecimal getPriceAud() {
			return priceAud;
		}

		public void setPriceAud(BigDecimal priceAud) {
			this.priceAud = priceAud;
		}

		public BigDecimal getPriceBrl() {
			return priceBrl;
		}

		public void setPriceBrl(BigDecimal priceBrl) {
			this.priceBrl = priceBrl;
		}

		public BigDecimal getPriceCad() {
			return priceCad;
		}

		public void setPriceCad(BigDecimal priceCad) {
			this.priceCad = priceCad;
		}

		public BigDecimal getPriceChf() {
			return priceChf;
		}

		public void setPriceChf(BigDecimal priceChf) {
			this.priceChf = priceChf;
		}

		public BigDecimal getPriceClp() {
			return priceClp;
		}

		public void setPriceClp(BigDecimal priceClp) {
			this.priceClp = priceClp;
		}

		public BigDecimal getPriceCny() {
			return priceCny;
		}

		public void setPriceCny(BigDecimal priceCny) {
			this.priceCny = priceCny;
		}

		public BigDecimal getPriceCzk() {
			return priceCzk;
		}

		public void setPriceCzk(BigDecimal priceCzk) {
			this.priceCzk = priceCzk;
		}

		public BigDecimal getPriceDkk() {
			return priceDkk;
		}

		public void setPriceDkk(BigDecimal priceDkk) {
			this.priceDkk = priceDkk;
		}

		public BigDecimal getPriceEur() {
			return priceEur;
		}

		public void setPriceEur(BigDecimal priceEur) {
			this.priceEur = priceEur;
		}

		public BigDecimal getPriceGbp() {
			return priceGbp;
		}

		public void setPriceGbp(BigDecimal priceGbp) {
			this.priceGbp = priceGbp;
		}

		public BigDecimal getPriceHkd() {
			return priceHkd;
		}

		public void setPriceHkd(BigDecimal priceHkd) {
			this.priceHkd = priceHkd;
		}

		public BigDecimal getPriceHuf() {
			return priceHuf;
		}

		public void setPriceHuf(BigDecimal priceHuf) {
			this.priceHuf = priceHuf;
		}

		public BigDecimal getPriceIdr() {
			return priceIdr;
		}

		public void setPriceIdr(BigDecimal priceIdr) {
			this.priceIdr = priceIdr;
		}

		public BigDecimal getPriceIls() {
			return priceIls;
		}

		public void setPriceIls(BigDecimal priceIls) {
			this.priceIls = priceIls;
		}

		public BigDecimal getPriceInr() {
			return priceInr;
		}

		public void setPriceInr(BigDecimal priceInr) {
			this.priceInr = priceInr;
		}

		public BigDecimal getPriceJpy() {
			return priceJpy;
		}

		public void setPriceJpy(BigDecimal priceJpy) {
			this.priceJpy = priceJpy;
		}

		public BigDecimal getPriceKrw() {
			return priceKrw;
		}

		public void setPriceKrw(BigDecimal priceKrw) {
			this.priceKrw = priceKrw;
		}

		public BigDecimal getPriceMxn() {
			return priceMxn;
		}

		public void setPriceMxn(BigDecimal priceMxn) {
			this.priceMxn = priceMxn;
		}

		public BigDecimal getPriceMyr() {
			return priceMyr;
		}

		public void setPriceMyr(BigDecimal priceMyr) {
			this.priceMyr = priceMyr;
		}

		public BigDecimal getPriceNok() {
			return priceNok;
		}

		public void setPriceNok(BigDecimal priceNok) {
			this.priceNok = priceNok;
		}

		public BigDecimal getPriceNzd() {
			return priceNzd;
		}

		public void setPriceNzd(BigDecimal priceNzd) {
			this.priceNzd = priceNzd;
		}

		public BigDecimal getPricePhp() {
			return pricePhp;
		}

		public void setPricePhp(BigDecimal pricePhp) {
			this.pricePhp = pricePhp;
		}

		public BigDecimal getPricePkr() {
			return pricePkr;
		}

		public void setPricePkr(BigDecimal pricePkr) {
			this.pricePkr = pricePkr;
		}

		public BigDecimal getPricePln() {
			return pricePln;
		}

		public void setPricePln(BigDecimal pricePln) {
			this.pricePln = pricePln;
		}

		public BigDecimal getPriceRub() {
			return priceRub;
		}

		public void setPriceRub(BigDecimal priceRub) {
			this.priceRub = priceRub;
		}

		public BigDecimal getPriceSek() {
			return priceSek;
		}

		public void setPriceSek(BigDecimal priceSek) {
			this.priceSek = priceSek;
		}

		public BigDecimal getPriceSgd() {
			return priceSgd;
		}

		public void setPriceSgd(BigDecimal priceSgd) {
			this.priceSgd = priceSgd;
		}

		public BigDecimal getPriceThb() {
			return priceThb;
		}

		public void setPriceThb(BigDecimal priceThb) {
			this.priceThb = priceThb;
		}

		public BigDecimal getPriceTry() {
			return priceTry;
		}

		public void setPriceTry(BigDecimal priceTry) {
			this.priceTry = priceTry;
		}

		public BigDecimal getPriceTwd() {
			return priceTwd;
		}

		public void setPriceTwd(BigDecimal priceTwd) {
			this.priceTwd = priceTwd;
		}

		public BigDecimal getPriceZar() {
			return priceZar;
		}

		public void setPriceZar(BigDecimal priceZar) {
			this.priceZar = priceZar;
		}

		
    }

    public class Global {

        @SerializedName("total_market_cap_usd")
        @Expose
        private BigDecimal totalMarketCapUsd;
        @SerializedName("total_24h_volume_usd")
        @Expose
        private BigDecimal total24hVolumeUsd;
        @SerializedName("bitcoin_percentage_of_market_cap")
        @Expose
        private BigDecimal bitcoinPercentageOfMarketCap;
        @SerializedName("active_currencies")
        @Expose
        private Integer activeCurrencies;
        @SerializedName("active_assets")
        @Expose
        private Integer activeAssets;
        @SerializedName("active_markets")
        @Expose
        private Integer activeMarkets;

        public BigDecimal getTotalMarketCapUsd() {
            return totalMarketCapUsd;
        }

        public void setTotalMarketCapUsd(BigDecimal totalMarketCapUsd) {
            this.totalMarketCapUsd = totalMarketCapUsd;
        }

        public BigDecimal getTotal24hVolumeUsd() {
            return total24hVolumeUsd;
        }

        public void setTotal24hVolumeUsd(BigDecimal total24hVolumeUsd) {
            this.total24hVolumeUsd = total24hVolumeUsd;
        }

        public BigDecimal getBitcoinPercentageOfMarketCap() {
            return bitcoinPercentageOfMarketCap;
        }

        public void setBitcoinPercentageOfMarketCap(BigDecimal bitcoinPercentageOfMarketCap) {
            this.bitcoinPercentageOfMarketCap = bitcoinPercentageOfMarketCap;
        }

        public Integer getActiveCurrencies() {
            return activeCurrencies;
        }

        public void setActiveCurrencies(Integer activeCurrencies) {
            this.activeCurrencies = activeCurrencies;
        }

        public Integer getActiveAssets() {
            return activeAssets;
        }

        public void setActiveAssets(Integer activeAssets) {
            this.activeAssets = activeAssets;
        }

        public Integer getActiveMarkets() {
            return activeMarkets;
        }

        public void setActiveMarkets(Integer activeMarkets) {
            this.activeMarkets = activeMarkets;
        }
    }

    private class Error {

        public String error;
    }

    private String getParamSuffix(String[][] params) {
        String str = null;
        for (String[] p : params) {
            str = (p != null && p.length > 1 && p[1] != null && p[0] != null) ? (str == null ? ("?" + p[0] + "=" + p[1]) : (str + "&" + p[0] + "=" + p[1])) : str;
        }
        return str == null ? "" : str;
    }

    private String getJsonResponse(final String url) throws CoinMarketCapException {

//        System.out.println("RATE INIT " + new Date());
        //maybe waits globally
        if (throttle) {
            rateLimiter.acquire();
        }
//        System.out.println("RATE CLOSe " + new Date());

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            final int responseCode = con.getResponseCode();

            StringBuffer response;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            if (responseCode != 200) {
                final Error error = gson.fromJson(response.toString(), Error.class);
                throw new CoinMarketCapException(responseCode, error == null ? null : error.error);
            }
            return response.toString();
        } catch (FileNotFoundException ex) {
            throw new CoinMarketCapException(400, "id not found");
        } catch (IOException ex) {
            throw new CoinMarketCapException(ex.getCause());
        }
    }

    public class CoinMarketCapException extends Exception {

        private final Integer status;

        public int getStatus() {
            return status;
        }

        public CoinMarketCapException(final int status, final String message) {
            super(message);
            this.status = status;
        }

        public CoinMarketCapException(Throwable cause) {
            super(cause);
            this.status = null;
        }

    }
    
    public List<Ticker> getTicker() throws CoinMarketCapException{
        return getTicker(null, null);
    }

    public List<Ticker> getTicker(final Integer limit, final CurrencyConvert convert) throws CoinMarketCapException {
        final String[][] params = {{"limit", (limit == null ? null : limit + "")}, {"convert", (convert == null ? null : convert + "")}};
        return gson.fromJson(getJsonResponse("https://api.coinmarketcap.com/v1/ticker/" + getParamSuffix(params)), new TypeToken<List<Ticker>>() {
        }.getType());
    }

    public Ticker getTickerById(final String id, final CurrencyConvert convert) throws CoinMarketCapException {
        if (id == null) {
            throw new CoinMarketCapException(400, "id null");
        }
        final String[][] params = {{"convert", (convert == null ? null : convert + "")}};
        List<Ticker> l = gson.fromJson(getJsonResponse("https://api.coinmarketcap.com/v1/ticker/" + id + "/" + getParamSuffix(params)), new TypeToken<List<Ticker>>() {
        }.getType());
        if (l.isEmpty()) {
            return null;
        }
        return l.get(0);
    }
    
    public Ticker getTickerById(final String id) throws CoinMarketCapException{
        return getTickerById(id, null);
    }

    public Global getGlobal(final CurrencyConvert convert) throws CoinMarketCapException {
        final String[][] params = {{"convert", (convert == null ? null : convert + "")}};
        return gson.fromJson(getJsonResponse("https://api.coinmarketcap.com/v1/global/" + getParamSuffix(params)), Global.class);
    }
    
    public Global getGlobal() throws CoinMarketCapException{
        return getGlobal(null);
    }

}