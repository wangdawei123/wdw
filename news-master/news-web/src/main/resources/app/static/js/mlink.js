!
function(e) {
	function t(o) {
		if (n[o]) return n[o].exports;
		var i = n[o] = {
			exports: {},
			id: o,
			loaded: !1
		};
		return e[o].call(i.exports, i, i.exports, t), i.loaded = !0, i.exports
	}
	var n = {};
	return t.m = e, t.c = n, t.p = "https://a.mlinks.cc/scripts/dist/", t(0)
}([function(e, t, n) {
	"use strict";

	function o(e) {
		return e && e.__esModule ? e : {
		default:
			e
		}
	}
	Object.defineProperty(t, "__esModule", {
		value: !0
	});
	var i = n(11),
		r = o(i);
	r.
default.env = r.
default.env || {}, r.
default.env.model = "open", t.
default = r.
default
}, function(e, t, n) {
	"use strict";

	function o(e) {
		return e && e.__esModule ? e : {
		default:
			e
		}
	}
	function i(e, t) {
		if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
	}
	Object.defineProperty(t, "__esModule", {
		value: !0
	});
	var r = function() {
			function e(e, t) {
				for (var n = 0; n < t.length; n++) {
					var o = t[n];
					o.enumerable = o.enumerable || !1, o.configurable = !0, "value" in o && (o.writable = !0), Object.defineProperty(e, o.key, o)
				}
			}
			return function(t, n, o) {
				return n && e(t.prototype, n), o && e(t, o), t
			}
		}(),
		a = n(8),
		s = o(a),
		u = n(1),
		c = (o(u), n(4)),
		l = (o(c), n(2)),
		d = (o(l), window.navigator.userAgent),
		h = [{
			value: "hwry6",
			key: "H60-L11"
		}, {
			value: "hwmt",
			key: "HUAWEI NXT-AL10"
		}, {
			value: "smnote4",
			key: "SAMSUNG-SM-N9100"
		}, {
			value: "smnote4",
			key: "SAMSUNG-SM-N9108V"
		}, {
			value: "iPhone",
			key: "iPhone;"
		}, {
			value: "iPad",
			key: "iPad;"
		}],
		f = h.map(function(e) {
			return e.key
		}).join("|"),
		p = function() {
			function e() {
				i(this, e), this.exportDeviceInfo()
			}
			return r(e, [{
				key: "exportDeviceInfo",
				value: function() {
					var e = this,
						t = d.match(new RegExp(f));
					h.forEach(function(n) {
						var o = n.value;
						t && t[0] === n.key ? e[o] = !0 : "undefined" == typeof e[o] && (e[o] = !1)
					})
				}
			}, {
				key: "getScreenResolution",
				value: function() {
					var e = window.screen,
						t = void 0;
					t = window.devicePixelRatio || 1, t = Math.max(2, t), e.width >= 1080 && (t = 1);
					var n = e.width * t,
						o = e.height * t,
						i = this.getSimilarResolution(n, o);
					return i[2] < 5 && i[3] < 5 && (n = i[0], o = i[1]), {
						width: n,
						height: o
					}
				}
			}, {
				key: "getSimilarResolution",
				value: function(e, t) {
					var n = [];
					return s.
				default.forEach(function(o) {
						var i = [].concat(o),
							r = Math.abs(o[0] - e),
							a = Math.abs(o[1] - t);
						i = i.concat([r, a]), n.push(i)
					}), n.sort(function(e, t) {
						return e[2] > t[2] || e[3] > t[3]
					}), n[0]
				}
			}]), e
		}();
	t.
default = new p
}, function(e, t, n) {
	"use strict";

	function o(e) {
		return e && e.__esModule ? e : {
		default:
			e
		}
	}
	function i(e, t) {
		if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
	}
	Object.defineProperty(t, "__esModule", {
		value: !0
	});
	var r = function() {
			function e(e, t) {
				for (var n = 0; n < t.length; n++) {
					var o = t[n];
					o.enumerable = o.enumerable || !1, o.configurable = !0, "value" in o && (o.writable = !0), Object.defineProperty(e, o.key, o)
				}
			}
			return function(t, n, o) {
				return n && e(t.prototype, n), o && e(t, o), t
			}
		}(),
		a = n(1),
		s = (o(a), window.navigator.userAgent),
		u = [{
			value: "android",
			key: "Android"
		}, {
			value: "symbian",
			key: "Symbian"
		}, {
			value: "blackberry",
			key: "BlackBerry"
		}, {
			value: "webos",
			key: "hpwOS"
		}, {
			value: "wp",
			key: "Windows Phone OS"
		}, {
			value: "ffos",
			key: "Firefox OS"
		}, {
			value: "ios",
			key: "iPhone OS"
		}, {
			value: "linux",
			key: "Linux"
		}, {
			value: "windows",
			key: "Windows NT"
		}, {
			value: "mac",
			key: "Macintosh"
		}, {
			value: "ipad",
			key: "iPad; CPU OS"
		}, {
			value: "osx",
			key: "Mac OS X"
		}, {
			value: "ubuntu",
			key: "Ubuntu"
		}, {
			value: "mobile",
			key: "Mobile"
		}],
		c = "(" + u.map(function(e) {
			return e.key
		}).join("|") + ")[\\s\\/]*([\\d\\_\\.]*)",
		l = function() {
			function e() {
				i(this, e), this.exportOsInfo()
			}
			return r(e, [{
				key: "exportOsInfo",
				value: function() {
					var e = this,
						t = s.match(new RegExp(c, "g")) || [];
					u.forEach(function(n) {
						t.forEach(function(t) {
							var o = t.match(new RegExp(c)),
								i = n.value,
								r = [0];
							o && o[1] === n.key ? (r = o[2] ? o[2].split(/[_\.]/) : r, r = r.map(function(e) {
								return parseInt(e)
							}), e[i] = !0, e[i + "Version"] = r) : "undefined" == typeof e[i] && (e[i] = !1, e[i + "Version"] = r)
						})
					}), this.ipad && (this.iosVersion = this.ipadVersion), this.ios = this.ios || this.ipad, this.osx = this.osx && this.mac, this.desktop = this.windows || this.mac || this.ubuntu || this.linux && !this.android && !this.mobile && !this.wp, this.mobile = this.mobile || this.android || this.ios || this.wp || this.ffos || this.symbian || this.blackberry
				}
			}]), e
		}();
	t.
default = new l
}, function(e, t) {
	"use strict";

	function n(e, t) {
		if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
	}
	Object.defineProperty(t, "__esModule", {
		value: !0
	});
	var o = function() {
			function e(e, t) {
				for (var n = 0; n < t.length; n++) {
					var o = t[n];
					o.enumerable = o.enumerable || !1, o.configurable = !0, "value" in o && (o.writable = !0), Object.defineProperty(e, o.key, o)
				}
			}
			return function(t, n, o) {
				return n && e(t.prototype, n), o && e(t, o), t
			}
		}(),
		i = function() {
			function e() {
				var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "";
				n(this, e);
				var o = t.match(/(\w+):\/\/([^:\/\?]*):?(\d*)([^\?]*)\??([^#$]*)#?([^#]*)/) || [];
				this.scheme = o[1] || "", this.protocol = o[1] || "", this.domain = o[2] || "", this.host = o[2] || "", this.port = o[3] || "", this.path = o[4] || "", this.queryString = o[5] || "", this.search = o[5] || "", this.hash = o[6] || ""
			}
			return o(e, [{
				key: "getParams",
				value: function() {
					var e = this.queryString.split("&"),
						t = {};
					return e.forEach(function(e) {
						var n = e.split("=");
						n[0] && (t[n[0]] = n[1])
					}), t
				}
			}, {
				key: "getParamsStartWith",
				value: function(e) {
					var t = new RegExp("^" + e),
						n = this.getParams(),
						o = {};
					for (var i in n) t.test(i) && ("mw_cp_" === e || "mw_dynp_" === e ? o[i.replace(e, "")] = n[i] : o[i] = n[i]);
					return o
				}
			}, {
				key: "getParam",
				value: function(e) {
					return this.getParams()[e]
				}
			}, {
				key: "setParam",
				value: function(e, t) {
					var n = this.getParams(),
						o = [];
					"undefined" == typeof t ? delete n[e] : n[e] = t;
					for (var i in n) o.push(i + "=" + n[i]);
					return this.queryString = o.join("&"), this
				}
			}, {
				key: "setParams",
				value: function(e) {
					var t = this.getParams(),
						n = [];
					for (var o in e) null === t[o] ? delete t[o] : t[o] = e[o];
					for (var i in t) n.push(i + "=" + t[i]);
					return this.queryString = n.join("&"), this
				}
			}, {
				key: "toString",
				value: function() {
					var e = [];
					return e.push(this.scheme ? this.scheme + "://" : ""), e.push(this.host), e.push(this.port ? ":" + this.port : ""), e.push(this.path), e.push(this.queryString ? "?" + this.queryString : ""), e.push(this.hash ? "#" + this.hash : ""), e.join("")
				}
			}]), e
		}();
	t.
default = i
}, function(e, t, n) {
	"use strict";

	function o(e) {
		return e && e.__esModule ? e : {
		default:
			e
		}
	}
	function i(e, t) {
		if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
	}
	Object.defineProperty(t, "__esModule", {
		value: !0
	});
	var r = function() {
			function e(e, t) {
				for (var n = 0; n < t.length; n++) {
					var o = t[n];
					o.enumerable = o.enumerable || !1, o.configurable = !0, "value" in o && (o.writable = !0), Object.defineProperty(e, o.key, o)
				}
			}
			return function(t, n, o) {
				return n && e(t.prototype, n), o && e(t, o), t
			}
		}(),
		a = n(2),
		s = o(a),
		u = n(1),
		c = o(u),
		l = n(3),
		d = o(l),
		h = window.navigator.userAgent,
		f = [{
			key: "Chrome",
			value: "chrome"
		}, {
			key: "CriOS",
			value: "CriOS",
			unambiguous: !0
		}, {
			key: "AppleWebkit",
			value: "webkit"
		}, {
			key: "AppleWebKit",
			value: "webkit"
		}, {
			key: "Safari",
			value: "safari"
		}, {
			key: "Opera",
			value: "opera",
			unambiguous: !0
		}, {
			key: "Firefox",
			value: "firefox",
			unambiguous: !0
		}, {
			key: "Firefox",
			value: "firefox",
			unambiguous: !0
		}, {
			key: "FxiOS",
			value: "fxios",
			unambiguous: !0
		}, {
			key: "MQQBrowser",
			value: "qqbrowser",
			unambiguous: !0
		}, {
			key: "QQ",
			value: "qq",
			unambiguous: !0
		}, {
			key: "Qzone",
			value: "qzone",
			unambiguous: !0
		}, {
			key: "Maxthon",
			value: "maxthon",
			unambiguous: !0
		}, {
			key: "UCWEB",
			value: "ucweb",
			unambiguous: !0
		}, {
			key: "SogouMobileBrowser",
			value: "sogou",
			unambiguous: !0
		}, {
			key: "UCBrowser",
			value: "uc",
			unambiguous: !0
		}, {
			key: "SE",
			value: "se",
			unambiguous: !0
		}, {
			key: "360SE",
			value: "360se",
			unambiguous: !0
		}, {
			key: "IEMobile",
			value: "iem",
			unambiguous: !0
		}, {
			key: "MSIE",
			value: "ie",
			unambiguous: !0
		}, {
			key: "weibo",
			value: "weibo",
			unambiguous: !0
		}, {
			key: "MagicWindow",
			value: "mw",
			unambiguous: !0
		}, {
			key: "SamsungBrowser",
			value: "samsung"
		}, {
			key: "QHBrowser",
			value: "qh360",
			unambiguous: !0
		}, {
			key: "Mb2345Browser",
			value: "mb2345",
			unambiguous: !0
		}, {
			key: "TencentTraveler",
			value: "tt",
			unambiguous: !0
		}, {
			key: "MxBrowser",
			value: "mx",
			unambiguous: !0
		}, {
			key: "BAIDUBrowser",
			value: "baidu",
			unambiguous: !0
		}, {
			key: "baidubrowser",
			value: "baidu",
			unambiguous: !0
		}, {
			key: "MicroMessenger",
			value: "wx",
			unambiguous: !0
		}, {
			key: "LieBaoFast",
			value: "liebao",
			unambiguous: !0
		}, {
			key: "AlipayClient/",
			value: "aliPay",
			unambiguous: !0
		}],
		p = "(" + f.map(function(e) {
			return e.key
		}).join("|") + ")[\\s_{3}\\/]*([\\d\\_\\.]*)",
		m = function() {
			function e() {
				var t = this;
				i(this, e);
				var n = h.match(new RegExp(p, "g")) || [];
				f.forEach(function(e) {
					n.forEach(function(n) {
						var o = n.match(new RegExp(p)),
							i = e.value,
							r = void 0;
						o && o[1] === e.key ? (r = o[2] || "", r = r.replace(/(^[\._\/]*|[\._\/]*$)/g, ""), r = r ? r.split(/[_\.]/) : [0], r = r.map(function(e) {
							return parseInt(e)
						}), t[i] = !0, t[i + "Version"] = r, e.unambiguous === !0 && "undefined" == typeof t.unambiguous && (t.unambiguous = !0)) : "undefined" == typeof t[i] && (t[i + "Version"] = [0])
					})
				});
				var o = this.unambiguous;
				if (this.chrome = this.chrome || this.CriOS, this.chrome = this.chrome && navigator.vendor.indexOf("Google") !== -1 && window.chrome, this.chrome = this.chrome && !o, this.chrome = !! this.chrome, this.safari = this.safari && navigator.vendor.indexOf("Apple") !== -1, this.safari = this.safari && (s.
			default.ios || s.
			default.osx), this.safari = this.safari && !this.chrome && !o, this.safari = !! this.safari, this.iosWebView = s.
			default.ios && this.webkit && !this.unambiguous && !this.safari && !this.chrome, this.androidWebView = s.
			default.android && this.webkit && !this.unambiguous && !this.chrome, this.wx && (this.qqbrowser = !1, this.qqbrowserVersion = []), this.mw) {
					var r = this.magicWindowConfig = {},
						a = h.match(/(MagicWindow\;).*?(?=\))/, "g");
					if (a && a[0]) {
						var u = a[0].replace(/MagicWindow\;/g, "");
						u = u.split(";"), u.length > 0 && u.forEach(function(e) {
							e = e.split("/"), e[0] && e[1] && (r[e[0]] = e[1])
						})
					}
					var c = this.magicWindowConfig.sv.split(".");
					this.mwVersion = c
				}
				this.qzone && (this.qq = !1)
			}
			return r(e, [{
				key: "isSupportedScheme",
				value: function() {
					var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {},
						t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : "auto";
					return "auto" === t ? this.isSupportedSchemeAuto(e) : this.isSupportedSchemeClick(e)
				}
			}, {
				key: "isSupportedSchemeAuto",
				value: function() {
					var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {};
					return !c.
				default.smnote4 && (!this.weibo && (!((this.wx || this.qq) && !this.isSupportedYYB(e)) && e.isSupportedScheme !== !1))
				}
			}, {
				key: "isSupportedSchemeClick",
				value: function() {
					var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {};
					return this.isSupportedSchemeAuto(e) || this.isSupportedSchemeOnlyClick(e), !1
				}
			}, {
				key: "isSupportedSchemeOnlyClick",
				value: function() {
					var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {},
						t = this.qq && this.qqVersion[0] >= 6;
					return !!(s.
				default.android && e.isSupportedSchemeClick || this.chrome || t || this.aliPay || this.baidu || this.qqbrowser)
				}
			}, {
				key: "isSupportedSchemeOnlyHref",
				value: function() {
					var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {};
					if (!e.isSupportedSchemeClick) return !1;
					if (s.
				default.ios) {
						if (this.qq && this.qqVersion[0] >= 6) return !0
					} else if (this.SamsungBrowser) return !0;
					return !1
				}
			}, {
				key: "isSupportedUniversalLink",
				value: function() {
					var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {};
					if (s.
				default.ios && s.
				default.iosVersion[0] >= 9 && e.iosUniversalLinkEnabled) {
						var t = !1;
						if (t = s.
					default.iosVersion[0] >= 10 ? this.weibo || this.wx || this.safari || this.aliPay || !this.unambiguous:
						this.weibo || this.wx || this.safari || this.aliPay, this.mw && this.magicWindowConfig && this.magicWindowConfig.sv) {
							var n = this.mwVersion,
								o = this.mw && n && (n[0] >= 4 || 3 == n[0] && n[1] > 9 || 3 == n[0] && 9 == n[1] && n[2] > 161125);
							if (s.
						default.ios && s.
						default.iosVersion[0] >= 9 && o) return !1;
							if (s.
						default.ios && s.
						default.iosVersion[0] >= 9 && !o) return !0
						}
						return !!t
					}
					return !1
				}
			}, {
				key: "isSupportedAppLink",
				value: function() {
					arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {};
					return !1
				}
			}, {
				key: "isSupportedYYB",
				value: function() {
					var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {},
						t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : "",
						n = new d.
					default (t);
					if ("dc" === n.getParam("mw_dc_order")) return !1;
					var o = s.
				default.ios ? e.iosYyb:
					e.androidYyb,
						i = s.
					default.ios && this.qzone,
						r = this.wx || i;
					return o && r
				}
			}, {
				key: "isSupportedIntent",
				value: function() {
					arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {};
					return !1
				}
			}]), e
		}();
	t.
default = new m
}, function(e, t, n) {
	var o, i;
	(function(r, a) {
		"use strict";
		var s = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ?
		function(e) {
			return typeof e
		} : function(e) {
			return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
		};
		/*!
		 * @overview es6-promise - a tiny implementation of Promises/A+.
		 * @copyright Copyright (c) 2014 Yehuda Katz, Tom Dale, Stefan Penner and contributors (Conversion to ES6 API by Jake Archibald)
		 * @license   Licensed under MIT license
		 *            See https://raw.githubusercontent.com/stefanpenner/es6-promise/master/LICENSE
		 * @version   3.3.1
		 */
		!
		function(r, a) {
			"object" === s(t) && "undefined" != typeof e ? e.exports = a() : (o = a, i = "function" == typeof o ? o.call(t, n, t, e) : o, !(void 0 !== i && (e.exports = i)))
		}(void 0, function() {
			function e(e) {
				return "function" == typeof e || "object" === ("undefined" == typeof e ? "undefined" : s(e)) && null !== e
			}
			function t(e) {
				return "function" == typeof e
			}
			function o(e) {
				J = e
			}
			function i(e) {
				$ = e
			}
			function u() {
				return function() {
					return r.nextTick(f)
				}
			}
			function c() {
				return function() {
					G(f)
				}
			}
			function l() {
				var e = 0,
					t = new ee(f),
					n = document.createTextNode("");
				return t.observe(n, {
					characterData: !0
				}), function() {
					n.data = e = ++e % 2
				}
			}
			function d() {
				var e = new MessageChannel;
				return e.port1.onmessage = f, function() {
					return e.port2.postMessage(0)
				}
			}
			function h() {
				var e = setTimeout;
				return function() {
					return e(f, 1)
				}
			}
			function f() {
				for (var e = 0; e < F; e += 2) {
					var t = oe[e],
						n = oe[e + 1];
					t(n), oe[e] = void 0, oe[e + 1] = void 0
				}
				F = 0
			}
			function p() {
				try {
					var e = n(14);
					return G = e.runOnLoop || e.runOnContext, c()
				} catch (e) {
					return h()
				}
			}
			function m(e, t) {
				var n = arguments,
					o = this,
					i = new this.constructor(g);
				void 0 === i[re] && O(i);
				var r = o._state;
				return r ? !
				function() {
					var e = n[r - 1];
					$(function() {
						return M(r, i, e, o._result)
					})
				}() : E(o, i, e, t), i
			}
			function v(e) {
				var t = this;
				if (e && "object" === ("undefined" == typeof e ? "undefined" : s(e)) && e.constructor === t) return e;
				var n = new t(g);
				return A(n, e), n
			}
			function g() {}
			function w() {
				return new TypeError("You cannot resolve a promise with itself")
			}
			function y() {
				return new TypeError("A promises callback cannot return that same promise.")
			}
			function b(e) {
				try {
					return e.then
				} catch (e) {
					return ce.error = e, ce
				}
			}
			function k(e, t, n, o) {
				try {
					e.call(t, n, o)
				} catch (e) {
					return e
				}
			}
			function _(e, t, n) {
				$(function(e) {
					var o = !1,
						i = k(n, t, function(n) {
							o || (o = !0, t !== n ? A(e, n) : P(e, n))
						}, function(t) {
							o || (o = !0, x(e, t))
						}, "Settle: " + (e._label || " unknown promise"));
					!o && i && (o = !0, x(e, i))
				}, e)
			}
			function S(e, t) {
				t._state === se ? P(e, t._result) : t._state === ue ? x(e, t._result) : E(t, void 0, function(t) {
					return A(e, t)
				}, function(t) {
					return x(e, t)
				})
			}
			function T(e, n, o) {
				n.constructor === e.constructor && o === m && n.constructor.resolve === v ? S(e, n) : o === ce ? x(e, ce.error) : void 0 === o ? P(e, n) : t(o) ? _(e, n, o) : P(e, n)
			}
			function A(t, n) {
				t === n ? x(t, w()) : e(n) ? T(t, n, b(n)) : P(t, n)
			}
			function C(e) {
				e._onerror && e._onerror(e._result), U(e)
			}
			function P(e, t) {
				e._state === ae && (e._result = t, e._state = se, 0 !== e._subscribers.length && $(U, e))
			}
			function x(e, t) {
				e._state === ae && (e._state = ue, e._result = t, $(C, e))
			}
			function E(e, t, n, o) {
				var i = e._subscribers,
					r = i.length;
				e._onerror = null, i[r] = t, i[r + se] = n, i[r + ue] = o, 0 === r && e._state && $(U, e)
			}
			function U(e) {
				var t = e._subscribers,
					n = e._state;
				if (0 !== t.length) {
					for (var o = void 0, i = void 0, r = e._result, a = 0; a < t.length; a += 3) o = t[a], i = t[a + n], o ? M(n, o, i, r) : i(r);
					e._subscribers.length = 0
				}
			}
			function L() {
				this.error = null
			}
			function I(e, t) {
				try {
					return e(t)
				} catch (e) {
					return le.error = e, le
				}
			}
			function M(e, n, o, i) {
				var r = t(o),
					a = void 0,
					s = void 0,
					u = void 0,
					c = void 0;
				if (r) {
					if (a = I(o, i), a === le ? (c = !0, s = a.error, a = null) : u = !0, n === a) return void x(n, y())
				} else a = i, u = !0;
				n._state !== ae || (r && u ? A(n, a) : c ? x(n, s) : e === se ? P(n, a) : e === ue && x(n, a))
			}
			function j(e, t) {
				try {
					t(function(t) {
						A(e, t)
					}, function(t) {
						x(e, t)
					})
				} catch (t) {
					x(e, t)
				}
			}
			function D() {
				return de++
			}
			function O(e) {
				e[re] = de++, e._state = void 0, e._result = void 0, e._subscribers = []
			}
			function q(e, t) {
				this._instanceConstructor = e, this.promise = new e(g), this.promise[re] || O(this.promise), Q(t) ? (this._input = t, this.length = t.length, this._remaining = t.length, this._result = new Array(this.length), 0 === this.length ? P(this.promise, this._result) : (this.length = this.length || 0, this._enumerate(), 0 === this._remaining && P(this.promise, this._result))) : x(this.promise, N())
			}
			function N() {
				return new Error("Array Methods must be provided an Array")
			}
			function R(e) {
				return new q(this, e).promise
			}
			function B(e) {
				var t = this;
				return new t(Q(e) ?
				function(n, o) {
					for (var i = e.length, r = 0; r < i; r++) t.resolve(e[r]).then(n, o)
				} : function(e, t) {
					return t(new TypeError("You must pass an array to race."))
				})
			}
			function W(e) {
				var t = this,
					n = new t(g);
				return x(n, e), n
			}
			function H() {
				throw new TypeError("You must pass a resolver function as the first argument to the promise constructor")
			}
			function V() {
				throw new TypeError("Failed to construct 'Promise': Please use the 'new' operator, this object constructor cannot be called as a function.")
			}
			function Y(e) {
				this[re] = D(), this._result = this._state = void 0, this._subscribers = [], g !== e && ("function" != typeof e && H(), this instanceof Y ? j(this, e) : V())
			}
			function z() {
				var e = void 0;
				if ("undefined" != typeof a) e = a;
				else if ("undefined" != typeof self) e = self;
				else try {
					e = Function("return this")()
				} catch (e) {
					throw new Error("polyfill failed because global object is unavailable in this environment")
				}
				var t = e.Promise;
				if (t) {
					var n = null;
					try {
						n = Object.prototype.toString.call(t.resolve())
					} catch (e) {}
					if ("[object Promise]" === n && !t.cast) return
				}
				e.Promise = Y
			}
			var K = void 0;
			K = Array.isArray ? Array.isArray : function(e) {
				return "[object Array]" === Object.prototype.toString.call(e)
			};
			var Q = K,
				F = 0,
				G = void 0,
				J = void 0,
				$ = function(e, t) {
					oe[F] = e, oe[F + 1] = t, F += 2, 2 === F && (J ? J(f) : ie())
				},
				X = "undefined" != typeof window ? window : void 0,
				Z = X || {},
				ee = Z.MutationObserver || Z.WebKitMutationObserver,
				te = "undefined" == typeof self && "undefined" != typeof r && "[object process]" === {}.toString.call(r),
				ne = "undefined" != typeof Uint8ClampedArray && "undefined" != typeof importScripts && "undefined" != typeof MessageChannel,
				oe = new Array(1e3),
				ie = void 0;
			ie = te ? u() : ee ? l() : ne ? d() : void 0 === X ? p() : h();
			var re = Math.random().toString(36).substring(16),
				ae = void 0,
				se = 1,
				ue = 2,
				ce = new L,
				le = new L,
				de = 0;
			return q.prototype._enumerate = function() {
				for (var e = this.length, t = this._input, n = 0; this._state === ae && n < e; n++) this._eachEntry(t[n], n)
			}, q.prototype._eachEntry = function(e, t) {
				var n = this._instanceConstructor,
					o = n.resolve;
				if (o === v) {
					var i = b(e);
					if (i === m && e._state !== ae) this._settledAt(e._state, t, e._result);
					else if ("function" != typeof i) this._remaining--, this._result[t] = e;
					else if (n === Y) {
						var r = new n(g);
						T(r, e, i), this._willSettleAt(r, t)
					} else this._willSettleAt(new n(function(t) {
						return t(e)
					}), t)
				} else this._willSettleAt(o(e), t)
			}, q.prototype._settledAt = function(e, t, n) {
				var o = this.promise;
				o._state === ae && (this._remaining--, e === ue ? x(o, n) : this._result[t] = n), 0 === this._remaining && P(o, this._result)
			}, q.prototype._willSettleAt = function(e, t) {
				var n = this;
				E(e, void 0, function(e) {
					return n._settledAt(se, t, e)
				}, function(e) {
					return n._settledAt(ue, t, e)
				})
			}, Y.all = R, Y.race = B, Y.resolve = v, Y.reject = W, Y._setScheduler = o, Y._setAsap = i, Y._asap = $, Y.prototype = {
				constructor: Y,
				then: m,
				catch: function(e) {
					return this.then(null, e)
				}
			}, z(), Y.polyfill = z, Y.Promise = Y, Y
		})
	}).call(t, n(7), function() {
		return this
	}())
}, function(e, t) {
	"use strict";
	e.exports = {
		suHost: "a.mlinks.cc",
		ulHost: "s.mlinks.cc",
		dpEvent: "stats.mlinks.cc",
		env: "prod"
	}
}, function(e, t) {
	"use strict";

	function n() {
		throw new Error("setTimeout has not been defined")
	}
	function o() {
		throw new Error("clearTimeout has not been defined")
	}
	function i(e) {
		if (l === setTimeout) return setTimeout(e, 0);
		if ((l === n || !l) && setTimeout) return l = setTimeout, setTimeout(e, 0);
		try {
			return l(e, 0)
		} catch (t) {
			try {
				return l.call(null, e, 0)
			} catch (t) {
				return l.call(this, e, 0)
			}
		}
	}
	function r(e) {
		if (d === clearTimeout) return clearTimeout(e);
		if ((d === o || !d) && clearTimeout) return d = clearTimeout, clearTimeout(e);
		try {
			return d(e)
		} catch (t) {
			try {
				return d.call(null, e)
			} catch (t) {
				return d.call(this, e)
			}
		}
	}
	function a() {
		m && f && (m = !1, f.length ? p = f.concat(p) : v = -1, p.length && s())
	}
	function s() {
		if (!m) {
			var e = i(a);
			m = !0;
			for (var t = p.length; t;) {
				for (f = p, p = []; ++v < t;) f && f[v].run();
				v = -1, t = p.length
			}
			f = null, m = !1, r(e)
		}
	}
	function u(e, t) {
		this.fun = e, this.array = t
	}
	function c() {}
	var l, d, h = e.exports = {};
	!
	function() {
		try {
			l = "function" == typeof setTimeout ? setTimeout : n
		} catch (e) {
			l = n
		}
		try {
			d = "function" == typeof clearTimeout ? clearTimeout : o
		} catch (e) {
			d = o
		}
	}();
	var f, p = [],
		m = !1,
		v = -1;
	h.nextTick = function(e) {
		var t = new Array(arguments.length - 1);
		if (arguments.length > 1) for (var n = 1; n < arguments.length; n++) t[n - 1] = arguments[n];
		p.push(new u(e, t)), 1 !== p.length || m || i(s)
	}, u.prototype.run = function() {
		this.fun.apply(null, this.array)
	}, h.title = "browser", h.browser = !0, h.env = {}, h.argv = [], h.version = "", h.versions = {}, h.on = c, h.addListener = c, h.once = c, h.off = c, h.removeListener = c, h.removeAllListeners = c, h.emit = c, h.binding = function(e) {
		throw new Error("process.binding is not supported")
	}, h.cwd = function() {
		return "/"
	}, h.chdir = function(e) {
		throw new Error("process.chdir is not supported")
	}, h.umask = function() {
		return 0
	}
}, function(e, t) {
	"use strict";
	Object.defineProperty(t, "__esModule", {
		value: !0
	}), t.
default = [
		["480", "800"],
		["480", "854"],
		["720", "1280"],
		["1080", "1920"],
		["1440", "2560"],
		["600", "1024"],
		["800", "1280"],
		["1200", "1920"],
		["2048", "1536"],
		["2560", "1600"],
		["1280", "720"],
		["1920", "1080"],
		["280", "280"],
		["320", "290"],
		["320", "320"]
	]
}, function(e, t) {
	"use strict";

	function n(e, t) {
		if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
	}
	Object.defineProperty(t, "__esModule", {
		value: !0
	});
	var o = function() {
			function e(e, t) {
				for (var n = 0; n < t.length; n++) {
					var o = t[n];
					o.enumerable = o.enumerable || !1, o.configurable = !0, "value" in o && (o.writable = !0), Object.defineProperty(e, o.key, o)
				}
			}
			return function(t, n, o) {
				return n && e(t.prototype, n), o && e(t, o), t
			}
		}(),
		i = function() {
			function e() {
				n(this, e)
			}
			return o(e, null, [{
				key: "getCookie",
				value: function(e) {
					var t = new RegExp("(^|;)[ ]*" + e + "=([^;]*)"),
						n = t.exec(document.cookie);
					return n ? decodeURIComponent(n[2]) : null
				}
			}, {
				key: "setCookie",
				value: function(e, t, n, o, i, r) {
					var a = void 0;
					n && (a = new Date, a.setTime(a.getTime() + n)), document.cookie = e + "=" + encodeURIComponent(t) + (n ? ";expires=" + a.toGMTString() : "") + ";path=" + (o || "/") + (i ? ";domain=" + i : "") + (r ? ";secure" : "")
				}
			}]), e
		}();
	t.
default = i
}, function(e, t, n) {
	"use strict";

	function o(e) {
		return e && e.__esModule ? e : {
		default:
			e
		}
	}
	Object.defineProperty(t, "__esModule", {
		value: !0
	});
	var i = n(3),
		r = o(i);
	t.
default = function() {
		var e = void 0;
		if (e = new r.
	default (document.location.href).getParam("MW_DEBUG")) {
			var t, n;
			(t = window).alert.apply(t, arguments), (n = console).log.apply(n, arguments)
		}
	}
}, function(e, t, n) {
	(function(e) {
		"use strict";

		function o(e) {
			return e && e.__esModule ? e : {
			default:
				e
			}
		}
		function i(e, t) {
			if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}
		function r(e) {
			return !!e && e.constructor === Array
		}
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var a = function() {
				function e(e, t) {
					for (var n = 0; n < t.length; n++) {
						var o = t[n];
						o.enumerable = o.enumerable || !1, o.configurable = !0, "value" in o && (o.writable = !0), Object.defineProperty(e, o.key, o)
					}
				}
				return function(t, n, o) {
					return n && e(t.prototype, n), o && e(t, o), t
				}
			}(),
			s = n(5),
			u = o(s),
			c = n(1),
			l = o(c),
			d = n(2),
			h = o(d),
			f = n(3),
			p = o(f),
			m = n(4),
			v = o(m),
			g = n(12),
			w = o(g),
			y = n(10),
			b = o(y),
			k = n(13),
			_ = o(k),
			S = n(9),
			T = o(S),
			A = u.
		default.Promise,
			C = T.
		default,
			P = p.
		default,
			x = h.
		default,
			E = l.
		default,
			U = v.
		default,
			L = w.
		default,
			I = b.
		default,
			M = _.
		default,
			j = window.navigator.userAgent,
			D = 3,
			O = M.sha1(j),
			q = [],
			N = {},
			R = {},
			B = localStorage,
			W = function() {
				function e() {
					i(this, e);
					for (var t = arguments.length, n = Array(t), o = 0; o < t; o++) n[o] = arguments[o];
					var r = this.formatConfig(n),
						a = r.autoInit;
					this.jsonData = {}, this.config = r.config, this.render = new L(this), document.location.hostname == e.env.suHost && document.body.classList.add("official-page"), a !== !1 && this.init()
				}
				return a(e, [{
					key: "subDomainCheck",
					value: function(t) {
						var n = t.mlink,
							o = new p.
						default (n);
						return o.domain = e.env.suHost, o.host = e.env.suHost, t.mlink = o.toString(), t.domainUrl = n, t
					}
				}, {
					key: "init",
					value: function() {
						var t = this.config;
						console.log(t);
						if (r(t))!
						function() {
							var n = void 0;
							t = r(t) ? t : [t], L.each(t, function(t) {
								var o = "undefined" != typeof t.autoLaunchApp ? t.autoLaunchApp : t.autoRedirect;
								o !== !0 || n ? (t.autoLaunchApp = !1, new e(t)) : (n = !0, new e(t))
							})
						}();
						else if (this.checkConfig(t) === !0) {
							console.log("check config");
							t = this.subDomainCheck(t);
							var n = t.button;
							!n || n.constructor !== Array && n.constructor !== NodeList || !
							function() {
								var e = [];
								L.each(n, function(t) {
									t && (t.tagName && e.push(t), t.constructor === NodeList && L.each(t, function(t) {
										e.push(t)
									}))
								}), t.button = e
							}(), this.trackingH5(t.mlink), this.initButton(t);
							var o = "undefined" != typeof t.autoLaunchApp ? t.autoLaunchApp : t.autoRedirect;
							o === !0 && ("shorturl" === e.env.model ? this.redirect(t) : e.location(this.applyParamsToShorturl(t.domainUrl)))
						}
					}
				}, {
					key: "formatConfig",
					value: function() {
						var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : [],
							t = {},
							n = e.length,
							o = [];
						return e.forEach(function(e, i) {
							return i === n - 1 && "boolean" == typeof e ? void(t.autoInit = e) : void(e && e.constructor === Object ? o.push(e) : e && e.constructor === Array && (o = e))
						}), 1 === o.length && o[0].constructor === Object && (o = o[0]), t.config = o, t
					}
				}, {
					key: "checkConfig",
					value: function(t) {
						return t.mlink && t.button ? (t.mlink.match(/^https?/) || (t.mlink = "https://" + e.env.suHost + "/" + t.mlink.replace(/(^[\/\s]*)/, "").replace(/[\s\/]*$/, "")), !0) : (console.log("new Mlink([{"), t.mlink ? console.log('    "mlink" : "' + t.mlink + '",') : console.error('  "mlink" : "{xxxx}", // 短链KEY是必要参数, 不能为空.'), t.button ? console.log('    "button" : "a标签(DOM类型)"') : console.error('  "button" : "DOM类型, 您页面中打开App的链接." // 必要参数, 不能为空'), console.log("}]);"), !1)
					}
				}, {
					key: "initButton",
					value: function(t, n) {
						var o = this,
							i = t.button && "number" == typeof t.button.length ? t.button : [t.button],
							r = n ? "number" == typeof n.length ? n : [n] : null;
						r = r && r.length > 0 ? r : i;
						for (var a = !! new P(t.mlink).getParam("DO_NOT_TRACKING") || "open" === e.env.model, s = void 0, u = 0, c = r.length; u < c; u++) if (s = r[u], s && "function" == typeof s.setAttribute) {
							var l = s.getAttribute("mlink-handling");
							if (!l) {
								s.setAttribute("href", "javascript:void(0)");
								var d = function() {
										o.tracking(!0), "function" == typeof t.beforeClick && t.beforeClick(), o.redirect(t, !0)
									};
								s.onclick = d
							}
						}
						this.loadData(null, null, {
							doNotTracking: a
						}).then(function(n) {
							var i = o.config.mlink;
							n = o.mwToApp(n);
							var a = o.getUniversalLink(n),
								s = U.isSupportedUniversalLink(n) && a && o.config.inapp !== !0,
								u = x.ios ? n.iosLink : n.androidLink,
								c = u && U.isSupportedSchemeOnlyClick(n),
								l = s ? a : u,
								d = o.getDownloadUrl(n);
							if (l = o.passParamsToApp(l), s) {
								var h = new P(l);
								h.setParam("mw_func_ul_ru", encodeURIComponent(location.href)), l = h.toString()
							}
							if ("open" === e.env.model && (c || s) && !U.qq || (x.ios && x.iosVersion[0] < 9 || x.android) && U.qq && U.qqVersion[0] >= 6) {
								o.sendEvent(l);
								for (var f = function() {
										o.tracking(!0), o.sendEvent(l), "function" == typeof t.beforeClick && t.beforeClick(), document.location.href = l, s && x.ios && x.iosVersion[0] >= 9 && U.qq && U.qqVersion[0] >= 6 || c && !
										function() {
											var t = setTimeout(function() {
												if (U.isSupportedYYB(n, i)) o.openDownloadUrl(n.yybDownloadUrl || d);
												else if ("open" === e.env.model && (U.wx || U.weibo)) {
													var t = new P(o.config.domainUrl),
														r = !1;
													o.config.resetWeixinMenu === !0 && (t.setParam("resetWeixinMenu", 1), r = r || !t.getParam("resetWeixinMenu")), o.config.preventDownload === !0 && (t.setParam("preventDownload", 1), r = r || !t.getParam("preventDownload")), r ? e.location(t.toString()) : o.openDownloadUrl(d)
												} else o.openDownloadUrl(d)
											}, 1e3);
											o.onAppLaunched(function() {
												clearTimeout(t)
											})
										}()
									}, p = 0, m = r.length; p < m; p++) {
									var v = r[p],
										g = v.getAttribute("mlink-handling");
									if(v && "function" == typeof v.setAttribute && !g){
									v.setAttribute("mlink-handling", !0),
									 x.ios && x.iosVersion[0] < 9 && U.qq && U.qqVersion[0] >= 6 ? 
									 (v.setAttribute("href", o.passParamsToApp(u)), v.addEventListener("click", f)) : 
									 
									 x.ios && x.iosVersion[0] >= 9 && U.qq && U.qqVersion[0] >= 6 && !s ?

									  (v.setAttribute("href", o.passParamsToApp(u)), v.addEventListener("click", f)) : 

									  (v.setAttribute("href", "javascript:void(0)"), 
									  	v.onclick = function() {},
									  	 v.removeEventListener("click", f),
									  	  v.addEventListener("click", f))
									} 
								}
							} else if (x.ios && x.iosVersion[0] >= 9 && U.qq && U.qqVersion[0] >= 6) for (var w = 0, y = r.length; w < y; w++) {
								var b = r[w];
								b.setAttribute("mlink-handling", !0), b.setAttribute("href", o.passParamsToApp(u)), o.sendEvent(u), b.onclick = function() {
									o.tracking(!0), o.sendEvent(u);
									var t = setTimeout(function() {
										if (U.isSupportedYYB(n, i)) o.openDownloadUrl(n.yybDownloadUrl || d);
										else if ("open" === e.env.model && (U.wx || U.weibo)) {
											var t = new P(o.config.domainUrl),
												r = !1;
											o.config.resetWeixinMenu === !0 && (t.setParam("resetWeixinMenu", 1), r = r || !t.getParam("resetWeixinMenu")), o.config.preventDownload === !0 && (t.setParam("preventDownload", 1), r = r || !t.getParam("preventDownload")), r ? e.location(t.toString()) : o.openDownloadUrl(d)
										} else o.openDownloadUrl(d)
									}, 1e3);
									return o.onAppLaunched(function() {
										clearTimeout(t)
									}), !0
								}
							}
							"shorturl" === e.env.model && document.getElementById("icon").setAttribute("href", n.iconUrl + "?imageView/2/w/16/h/16/format/ico")
						})
					}
				}, {
					key: "redirect",
					value: function(t, n) {
						var o = this,
							i = this.config.mlink;
						if (x.desktop) return void this.showQrCode();
						var r = !! new P(t.mlink).getParam("DO_NOT_TRACKING") || "open" === e.env.model;
						this.loadData(null, null, {
							doNotTracking: r
						}).then(function(r) {
							I("load data end！"), "shorturl" === e.env.model && document.getElementById("icon").setAttribute("href", r.iconUrl + "?imageView/2/w/16/h/16/format/ico"), r = o.mwToApp(r), o.resetTitle(r.productName), E.resolution = r.resolution, U.supportScheme = r.supportScheme;
							var a = new P(i),
								s = x.android ? r.androidLink : r.iosLink,
								u = o.getDownloadUrl(r),
								c = o.isAppInstalled(),
								l = o.getUniversalLink(r);
							o.customDataView = "1" === a.getParam("mw_preview") && r.mwContent, o.applink = s, o.MW_ST = +new Date, o.MW_TQ = [], o.MW_TQ_TICKET = setInterval(function() {
								o.MW_TQ.push(0)
							}, 100), I("noUniversalLink start");
							var d = U.isSupportedUniversalLink(r) && !l;
							if (I("noUniversalLink end"), s || !d) {
								if (U.isSupportedUniversalLink(r) && l && o.config.inapp !== !0) {
									n = n || U.wx && "shorturl" === e.env.model, I("ul."), l = o.passParamsToApp(l);
									var h = new P(l);
									return h.setParam("mw_func_ul_ru", encodeURIComponent(location.href)), l = h.toString(), o.applink = l, void(n ? (o.sendEvent(l).then(function() {
										U.wx && "open" === e.env.model && (setTimeout(function() {
											window.location.reload()
										}, 1e3), B && B.setItem("mwDoNotTracking", o.config.mlink))
									}), o.location(l, function() {}, 600)) : (o.sendEvent(l), o.showUniversalLinkButton(l)))
								}
								var f = !1;
								U.qq && U.qqVersion[0] >= 6 && "open" !== e.env.model && (f = !0), (U.qqbrowser || U.qzone) && (f = !0);
								var p = U.isSupportedYYB(r, i);
								if (p && (u = r.yybDownloadUrl || u), p && !f) return I("YYB2 Event."), void o.sendEvent(s, null, 1).then(function() {
									I("Redirect to YYB."), o.location(u)
								});
								if (I("click scheme"), I("byClick " + n), !n && U.isSupportedSchemeOnlyClick(r)) return I("supported scheme by click"), o.sendEvent(s), void o.showSchemeButton(s);
								if (U.wx || U.qq || U.weibo || x.android && U.qzone) {
									if (I("wx | qq | weibo."), o.sendEvent(s), I("sended event"), !U.isSupportedSchemeOnlyClick(r)) {
										if ("open" === e.env.model) {
											var m = new P(o.applyParamsToShorturl(t.domainUrl));
											return t.resetWeixinMenu === !0 && m.setParam("resetWeixinMenu", 1), t.preventDownload === !0 && m.setParam("preventDownload", 1), void(window.location.href = m.toString())
										}
										if (I("isSupportedSchemeOnlyClick else"), n || new P(t.mlink).getParam("DO_NOT_TRACKING") + "" == "1") {
											if (I("byClick else"), U.wx && (x.android || x.iosVersion[0] < 9) && U.isSupportedYYB(r, i)) return void o.sendEvent(s, null, 1).then(function() {
												I("Redirect to YYB."), o.location(u)
											});
											o.createOpenInBrowserModal()
										} else {
											I("byClick else href");
											var v = new P(o.applyParamsToShorturl(t.domainUrl));
											v.setParam("DO_NOT_TRACKING", 1), window.location.href = v.toString()
										}
										return
									}
									if (I("isSupportedSchemeOnlyClick"), n) return I("byClick"), void o.openScheme(s, function() {
										"open" === e.env.model ? window.location.href = o.applyParamsToShorturl(t.domainUrl) : U.qq ? o.openDownloadUrl(u) : o.createOpenInBrowserModal()
									}, 500)
								}
								return U.isSupportedScheme(r) ? (I("default for scheme: " + s), o.sendEvent(s), I("isAppInstalled:" + c), void(c === !0 ? o.openScheme(s, function() {
									o.render.showSchemeNotSupport(u)
								}, 500) : c === !1 ? o.openDownloadUrl(u, !0) : o.openScheme(s, function() {
									I("Redirect to Download URL.1"), o.openDownloadUrl(u)
								}, 500))) : (I("not support scheme."), o.sendEvent(s), o.render.showSchemeNotSupport(u), void 0)
							}
							I("!scheme && noUniversalLink:" + !s + "-" + d);
							var g = !U.isSupportedSchemeOnlyClick(r) || n;
							if (g && "LANDING_PAGE" === r.jumpType && r.landingPage) I("goto landing page.."), e.location(r.landingPage);
							else {
								I("setting error.");
								var w = x.ios ? "iOS" : "Android",
									y = "很抱歉，" + r.productName + "暂未提供" + w + "对应的跳转服务。";
								o.render.showPage(".setting-error", {
									title: y,
									text: " "
								})
							}
						})
					}
				}, {
					key: "getApiHost",
					value: function(t) {
						var n = document.location.href,
							o = void 0,
							i = void 0;
						if (n.match(/^https:/) || n.match(/^http:/)) {
							var r = new P(t),
								a = r.path.replace(/\/su\//, "/") + "?" + r.queryString;
							i = "https://" + e.env.ulHost + "/su", o = i + a
						} else o = t;
						var s = new P(o);
						return s.setParam("REQUEST_TYPE", "POST"), s.toString()
					}
				}, {
					key: "getUniversalLink",
					value: function(e) {
						if (e) {
							var t = new P(e.ios9Link || e.universalLink);
							return U.magicWindowConfig, this.config.downloadWhenUniversalLinkFailed === !0 && (t.setParam("downloadWhenFailed", "1"), e.ios9Link = t.toString()), this.config.resetWeixinMenu === !0 && t.setParam("resetWeixinMenu", 1), t.toString()
						}
					}
				}, {
					key: "loadData",
					value: function(e, t) {
						var n = this,
							o = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {},
							i = 6e3,
							r = this,
							a = this.config,
							s = a.timeout || i,
							u = a.timeoutCallback ||
						function() {
							r.render.showPage(".server-error")
						};
						(isNaN(+s) || +s < 0) && (s = i, console.warn("timeout  must be number and >0")), "function" != typeof u && (u = function() {
							r.render.showPage(".server-error")
						}, console.warn("timeoutCallback must be function")), t = t || this.config.mlink, t = this.applyParamsToShorturl(t);
						var c = this.getApiHost(t);
						if (B && B.getItem("mwDoNotTracking") === t && (o.doNotTracking = !0, B.setItem("mwDoNotTracking", "")), o.doNotTracking !== !0 && delete o.doNotTracking, U.mw) {
							var l = U.magicWindowConfig && U.magicWindowConfig.ak;
							l && (o.inAk = l)
						}
						return R[t] && e !== !0 ? new A(function(e) {
							var n = R[t];
							e(n)
						}) : N[t] && e !== !0 ? N[t] : (N[t] = new A(function(e, i) {
							var r = new XMLHttpRequest,
								l = setTimeout(function() {
									console.warn("time out"), u(a), i()
								}, s);
							r.onreadystatechange = function() {
								if (4 == r.readyState) {
									if (200 == r.status) {
										clearTimeout(l);
										var o = n.parseJson(r.responseText);
										if (n.render.init(o), n.jsonData = o, n.config.preventDownload === !0 && o.ios9Link) {
											var a = new P(o.ios9Link);
											o.ios9Link = a.setParam("preventDownload", "1").toString()
										}
										if (n.config.sendDpEventWhenLoading) {
											var s = x.ios ? o.iosLink : o.androidLink,
												u = U.isSupportedUniversalLink(o) ? o.ios9Link : s;
											u = n.passParamsToApp(u), n.sendEvent(u)
										}
										R[t] = o, e(o)
									} else n.render.showPage(".server-error"), i();
									delete N[t]
								}
							}, r.open("POST", c, !0), r.setRequestHeader("Content-Type", "application/json;charset=UTF-8"), r.send(JSON.stringify(o))
						}), N[t])
					}
				}, {
					key: "applyParamsToShorturl",
					value: function(e) {
						var t = [],
							n = this.config.params || {};
						for (var o in n) t.push(o + "=" + encodeURIComponent(n[o]));
						var i = this.config.cparams || {};
						for (var r in i) t.push("mw_cp_" + r + "=" + encodeURIComponent(i[r]));
						var a = this.config.tparams || {};
						for (var s in a) t.push("mw_dynp_" + s + "=" + encodeURIComponent(a[s]));
						return t = t.sort(), t.length > 0 && (e += (e.indexOf("?") === -1 ? "?" : "&") + t.join("&")), e
					}
				}, {
					key: "parseJson",
					value: function(e) {
						var t = JSON.parse(e);
						return t = t ? t.result || {} : {}
					}
				}, {
					key: "resetTitle",
					value: function(t) {
						"shorturl" === e.env.model && (this.originTitle = this.originTitle || document.title, document.title = t || this.originTitle)
					}
				}, {
					key: "getProtocol",
					value: function(e) {
						var t = /^(\w+):\/\//,
							n = e.match(t);
						if (n) return n[1]
					}
				}, {
					key: "getDownloadUrl",
					value: function(e) {
						(this.jsonData.iosDownloadUrl || this.jsonData.androidDownloadUrl) && (e = this.jsonData);
						var t = e.landingPage,
							n = x.ios ? e.iosDownloadUrl : e.androidDownloadUrl,
							o = "";
						if (t) {
							var i = void 0;
							i = new P(this.config.mlink).getParams(), o = new P(t).setParams(i).toString()
						}
						return "LANDING_PAGE" === e.jumpType ? o : n
					}
				}, {
					key: "getEventUrl",
					value: function(t) {
						var n = document.location.href,
							o = t || (this.jsonData ? this.jsonData.server : "") || "https://" + e.env.dpEvent;
						return n.match(/^https:/) ? (o = e.env.dpEvent, o = (o.match(/^https:\/\//) ? "" : "https://") + o) : o = (o.match(/^http/) ? "" : "https://") + o, o = o.replace(/\/$/, ""), o + "/dp/event"
					}
				}, {
					key: "getScreenResolution",
					value: function() {
						var e = window.screen,
							t = void 0;
						t = x.ios ? window.devicePixelRatio : U.wx || U.uc || !window.devicePixelRatio ? 1 : window.devicePixelRatio;
						var n = e.width * t,
							o = e.height * t;
						return [n, o].join("x")
					}
				}, {
					key: "getShorturlKey",
					value: function() {
						var e = new P(this.config.mlink),
							t = e.path.replace(/^\//, "");
						return t = t.replace("su/", "")
					}
				}, {
					key: "sendEvent",
					value: function(e, t) {
						var n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : 0,
							o = this,
							i = arguments.length > 3 && void 0 !== arguments[3] ? arguments[3] : "",
							r = !(arguments.length > 4 && void 0 !== arguments[4]) || arguments[4],
							a = this.jsonData || {},
							s = "POST",
							u = this.getEventUrl(t),
							c = x.ios ? 1 : (x.android, 0),
							l = 1 === c ? j.match(/iPhone OS ([\d_\.]*)/) : j.match(/Android ([\d_\.]*)/),
							d = this.getShorturlKey(),
							h = E.getScreenResolution(),
							f = a.resX || h.width,
							p = a.resY || h.height,
							m = i || a.staticParams || "{}",
							v = {
								key: d,
								dp: r ? this.passParamsToApp(e) : e,
								sr: [f, p].join("x"),
								dt: JSON.parse(m),
								os: c,
								osv: l ? (l[1] || "").replace(/_/g, ".") : "",
								yyb: n
							};
						return I("send event start"), new A(function(a, c) {
							var l = void 0;
							l = new XMLHttpRequest, I("sending event"), 1 === n && o.sendEvent(e, t, 0, i, r), l && (l.open(s, u, !0), l.withCredentials = !0, l.setRequestHeader("Method", s + " " + u + " HTTP/1.1"), l.setRequestHeader("Content-type", "application/json"), l.send(JSON.stringify(v)), l.onreadystatechange = function() {
								4 === parseInt(l.readyState) && (200 === parseInt(l.status) ? a() : c())
							}, setTimeout(function() {
								a()
							}, 2e3))
						})
					}
				}, {
					key: "trackingH5",
					value: function(t) {
						var n = (new Date).getTime() + Math.floor(1e13 * Math.random()),
							o = new P(t),
							i = new P(window.location.href),
							r = i.getParam("mw_ck"),
							a = "https://" + e.env.dpEvent + "/tracking/i";
						a += "a.mlinks.cc" === i.domain || "at.mlinks.cc" === i.domain || "b.mlinks.cc" === i.domain || "bt.mlinks.cc" === i.domain ? "?ch=surl" : "?ch=h5", r && (a += "," + r);
						var s = encodeURIComponent(o.protocol + "://" + o.host + o.path);
						a += "&action=mwi&cid=" + s + "&rdm=" + n, setTimeout(function() {
							(new Image).src = a
						}, 0)
					}
				}, {
					key: "tracking",
					value: function() {
						var e = arguments.length > 0 && void 0 !== arguments[0] && arguments[0];
						this.btnClickedCounter = this.btnClickedCounter || 0, (this.btnClickedCounter > 0 || e) && this.loadData(!0), this.btnClickedCounter += 1
					}
				}, {
					key: "trackingDownload",
					value: function() {}
				}, {
					key: "getDeferredTime",
					value: function() {
						var e = +new Date,
							t = (e - this.MW_ST) / 1e3,
							n = t - this.MW_TQ.length / 10;
						return clearInterval(this.MW_TQ_TICKET), n
					}
				}, {
					key: "openDownloadUrl",
					value: function(t, n) {
						var o = this,
							i = new P(document.location.href);
						if (this.config.preventDownload !== !0 && !i.getParam("preventDownload")) {
							var r = n ? 0 : D,
								a = n ? 0 : 1e3,
								s = this.render.getCustomStyles().count_down_json || {};
							try {
								document.querySelector(".scheme-redirecting h3").style.color = s.countDownTextColor;
								var u = document.querySelector(".scheme-redirecting .tips");
								s.isOtherTextShow ? u.style.color = s.textColor : u.style.display = "none"
							} catch (e) {}
							var c = this.render.showPage(".scheme-redirecting", {
								bg: s.bgUrl
							}),
								l = void 0;
							if (I(c), !c) return void setTimeout(function() {
								if (o.isPageVisible()) {
									var n = "www.xingyun.cn" === document.location.hostname;
									o.config.autoRedirectToDownloadUrl === !1 || n ? o.render.showDownloadDialod() : e.location(t)
								}
							}, 1e3);
							var d = e.isIosLE9() && U.safari;
							if (!U.uc && !U.qqbrowser && !d) {
								r = 0;
								var h = c.querySelector(".timeSpan") || {};
								h.style.display = "none", a = 1e3
							}
							I("Interval Times: " + r);
							var f = c.querySelector(".time") || {};
							f.innerHTML = r ? Math.floor(r) + "秒" : "", l = setInterval(function() {
								f.innerHTML = r ? Math.floor(r - 1) + " 秒" : "0 秒", r -= 1;
								var n = !o.isPageVisible();
								return I(r + "; " + (r < 0)), n && !d ? (clearInterval(l), void o.render.showAppOpened(o.jsonData)) : void(r < 0 && (I("timeout"), clearInterval(l), o.render.showDownloadTips(t), e.location(t)))
							}, a), t.match(/:\/\/itunes\.apple\.com/) || t.match(/\.apk($|\?)/) || this.onAppLaunched(function() {
								clearInterval(l), I("Not APK & AppStore."), o.render.showAppOpened(o.jsonData)
							})
						}
					}
				}, {
					key: "isAppInstalled",
					value: function e() {
						var t = window.location.href,
							n = new P(t),
							o = "weixin" === n.getParam("platform"),
							e = "1" === n.getParam("isappinstalled");
						return o ? !! e : "unknow"
					}
				}, {
					key: "isPageVisible",
					value: function() {
						var e = void 0;
						return e = "undefined" != typeof document.hidden ? document.hidden : "undefined" != typeof document.mozHidden ? document.mozHidden : "undefined" != typeof document.msHidden ? document.msHidden : "undefined" != typeof document.webkitHidden ? document.webkitHidden : "undefined" != typeof window.JSInterface && !window.JSInterface.mwAppOpened(), !e
					}
				}, {
					key: "createOpenInBrowserModal",
					value: function() {
						var e = x.ios ? ".openinbrowser.ios" : ".openinbrowser.android",
							t = (this.jsonData || {}).iconUrl,
							n = this.render.getCustomStyles(),
							o = x.ios ? n.iosTipsImg : n.androidTipsImg,
							i = this.render.showPage(e, {
								appIcon: t
							});
						i && this.render.setTipsImg(i, o)
					}
				}, {
					key: "showQrCode",
					value: function() {
						var e = "https://a.mlinks.cc/qr/gen?margin=0&width=150&content=" + encodeURIComponent(this.config.mlink);
						this.render.showPage(".mw-alert", {
							title: "请扫描二维码在移动设备上访问",
							text: '<img class="qrcode-img" src="' + e + '">'
						})
					}
				}, {
					key: "getParam",
					value: function(e) {
						var t = window.document.location.href,
							n = t.match(new RegExp("[?&]" + e + "=(\\w*)[&$]")),
							o = n ? n[1] : "";
						return o
					}
				}, {
					key: "passParamsToApp",
					value: function(t) {
						if (t && t.indexOf("mw_mk=") == -1) {
							var n = this.jsonData ? this.jsonData : {},
								o = [],
								i = new P(this.config.mlink),
								r = i.getParam("mw_ck"),
								a = i.getParam("mw_dc_order"),
								s = i.getParam("mw_dc"),
								u = i.getParam("mw_ios_dc"),
								c = i.getParam("mw_android_dc");
							r && r.match(/(%\w{2})+/g) && (r = decodeURIComponent(r)), s && s.match(/(%\w{2})+/g) && (s = decodeURIComponent(s)), u && u.match(/(%\w{2})+/g) && (u = decodeURIComponent(u)), c && c.match(/(%\w{2})+/g) && (c = decodeURIComponent(c)), o.push(["mw_ck", encodeURIComponent(r || n.channel || "")].join("=")), o.push(["mw_dc", encodeURIComponent(s || "")].join("=")), o.push(["mw_dc_order", encodeURIComponent(a || "")].join("=")), o.push(["mw_ios_dc", encodeURIComponent(u || "")].join("=")), o.push(["mw_android_dc", encodeURIComponent(c || "")].join("=")), o.push(["mw_mk", encodeURIComponent(n.mLinkKey || "")].join("=")), o.push(["mw_slk", encodeURIComponent(this.getShorturlKey() || "")].join("=")), o.push(["mw_tags", encodeURIComponent(n.tags || "")].join("="));
							var l = this.config.cparams;
							l || (l = i.getParamsStartWith("mw_cp_") || {});
							for (var d in l) o.push(["mw_cp_" + d, encodeURIComponent(l[d])].join("="));
							var h = this.config.tparams;
							h || (h = i.getParamsStartWith("mw_dynp_") || {});
							for (var f in h) o.push(["mw_dynp_" + f, encodeURIComponent(h[f])].join("="));
							if (t.indexOf(e.env.ulHost !== -1)) {
								var p = new Function("return " + n.staticParams)();
								p && p.IOS && (p = p.IOS, delete p.DO_NOT_TRACKING, delete p.REQUEST_TYPE), p = JSON.stringify(p), o.push(["mw_ulp", encodeURIComponent(btoa(encodeURIComponent(p || "")))].join("="))
							}
							o.push(["mw_tk", encodeURI(O)].join("=")), o = o.join("&"), t = t + (t.indexOf("?") === -1 ? "?" : "&") + o, t = t.replace(/REQUEST_TYPE=(\w+)(&|$)/, "").replace(/&$/, "")
						}
						return t
					}
				}, {
					key: "onAppLaunched",
					value: function(e) {
						var t = this;
						e = "function" == typeof e ? e : function() {}, I("onAppLaunched");
						var n = function(e) {
								I(e.type)
							};
						window.addEventListener("beforeunload", n);
						var o = !1,
							i = function(n) {
								if (!o) {
									o = !0;
									var i = n.type;
									I("event", i), "webkitvisibilitychange" === i && t.isPageVisible() || e(), t.render.showAppOpened(t.jsonData)
								}
							};
						window.addEventListener("pagehide", i), window.addEventListener("unload", i), document.addEventListener("webkitvisibilitychange", i), document.addEventListener("visibilitychange", i)
					}
				}, {
					key: "showSchemeButton",
					value: function(e) {
						this.showRedirectButton(e, 0)
					}
				}, {
					key: "showUniversalLinkButton",
					value: function(e) {
						this.showRedirectButton(e, 1)
					}
				}, {
					key: "showRedirectButton",
					value: function(e) {
						var t = (arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : 1, arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : null),
							n = t || this.config.button,
							o = R[this.config.mlink] || {},
							i = o.productName || o.appName,
							r = o.iconUrl || o.appIcon,
							a = this.render.getCustomStyles(),
							s = a.open_app_json;
						n && (n.constructor !== Array && (n = [n]), this.initButton(this.config, n)), null === t && this.render.showPage(".redirect-with-button", {
							appIcon: r,
							appName: i,
							type: s.type,
							bg: s.bgUrl,
							btn: {
								innerHTML: s.btnText || "打开App",
								color: s.btnTextColor || "",
								backgroundColor: s.btnColor || "",
								btnDistanceBottom: s.btnDistanceBottom || "20%",
								href: U.aliPay && e
							}
						})
					}
				}, {
					key: "openScheme",
					value: function(e, t, n) {
						I(x.ios + ";" + x.ipad + x.iosVersion[0]);
						var o = t,
							i = U.mw && U.mwVersion,
							r = !1;
						i && (i[0] >= 4 || 3 == i[0] && i[1] > 9 || 3 == i[0] && 9 == i[1] && i[2] > 161125) && (r = !0), x.ios && x.iosVersion[0] >= 9 && r ? (I("Scheme with magicwindow."), this.openAppWithMagicwindow(e, o, n)) : x.ios && x.iosVersion[0] >= 9 && !r ? (I("Scheme with location."), this.location(e, o, n)) : x.android && U.chrome ? (I("Scheme with location."), this.location(e, o, n)) : x.ios && U.chrome ? (I("Scheme with window.open."), this.openAppWithWindowOpen(e, o, n)) : this.location(e, o, n)
					}
				}, {
					key: "openAppWithWindowOpen",
					value: function(e, t, n) {
						e = this.passParamsToApp(e);
						var o = void 0;
						try {
							o = window.open(e)
						} catch (e) {}
						o ? window.close() : t()
					}
				}, {
					key: "openAppWithMagicwindow",
					value: function(e, t, n) {
						var o = this,
							i = setTimeout(t, n || 500);
						e = this.passParamsToApp(e), window.MW_APP_OPEND_CALLBACK_FN = function(e) {
							I("MW_APP_OPEND_CALLBACK_FN:" + e), +e && (o.render.showAppOpened(o.jsonData, e), clearTimeout(i))
						}, webkit && webkit.messageHandlers && webkit.messageHandlers.MagicWindow && webkit.messageHandlers.MagicWindow.postMessage({
							method: "mwOpenUrl",
							data: JSON.stringify({
								urlScheme: e,
								callback: "MW_APP_OPEND_CALLBACK_FN"
							})
						})
					}
				}, {
					key: "location",
					value: function(t, n, o) {
						t = this.passParamsToApp(t), e.location(t);
						var i = setTimeout(n, o || 500);
						this.onAppLaunched(function() {
							clearTimeout(i)
						})
					}
				}, {
					key: "openAppWithIframe",
					value: function(e, t, n) {
						e = this.passParamsToApp(e);
						var o = document.createElement("iframe");
						o.style.width = "0px", o.style.height = "0px", o.style.overflow = "hidden", o.id = "mlinkIframeLauncher", o.src = e, document.body.appendChild(o);
						var i = setTimeout(t, n || 500);
						this.onAppLaunched(function() {
							clearTimeout(i)
						})
					}
				}, {
					key: "mwToApp",
					value: function(e) {
						if (!U.mw) return e;
						var t = {};
						t.mw_mlink_k = "webview", t.mw_mlink_appid = U.magicWindowConfig && U.magicWindowConfig.ak, t.mw_aba_appName = e.inProductName, t.mw_mlink_cb = e.inScheme;
						var n = e.ios9Link && new P(e.ios9Link),
							o = e.iosLink && new P(e.iosLink);
						for (var i in t) t[i] && (n && n.setParam(i, t[i]), o && o.setParam(i, t[i]));
						return e.ios9Link = n ? n.toString() : "", e.iosLink = o && o.toString(), e
					}
				}], [{
					key: "isIosLE9",
					value: function() {
						return x.ios && x.iosVersion[0] >= 9
					}
				}, {
					key: "location",
					value: function(e) {
						e && (document.location.href = e)
					}
				}, {
					key: "loadScript",
					value: function() {
						for (var e = [], t = document.querySelector("head"), n = arguments.length, o = Array(n), i = 0; i < n; i++) o[i] = arguments[i];
						return o.forEach(function(n) {
							var o = document.createElement("script"),
								i = new A(function(e, n) {
									o.onload = function() {
										e(), t.removeChild(o)
									}, o.onerror = function() {
										n(), t.removeChild(o)
									}
								});
							o.src = n, t.appendChild(o), e.push(i)
						}), A.all(e)
					}
				}, {
					key: "ready",
					value: function(e) {
						var t = document.readyState;
						"interactive" === t || "complete" === t ? e() : (q.push(e), document.addEventListener("readystatechange", function() {
							var e = void 0;
							if (t = document.readyState, "interactive" === t || "complete" === t) for (; q.length > 0;) e = q.shift(), "function" == typeof e && e()
						}))
					}
				}, {
					key: "scanLink",
					value: function() {
						var t = 1;
						e.ready(function() {
							setInterval(function() {
								var n = document.querySelectorAll("a");
								L.each(n, function(n) {
									var o = n.getAttribute("href"),
										i = !! n.getAttribute("data-mlink-scan"),
										r = !(!n.getAttribute("data-auto") && !n.getAttribute("auto")),
										a = n.getAttribute("data-params") || n.getAttribute("params");
									o && (o = new P(o)), !i && o && o.host && (o.host.match(/a(t)?\.mlinks\.cc/) || o.host.match(/b(t)?\.mlinks\.cc/)) && (a = new Function("return " + a)(), n.setAttribute("data-mlink-scan", t), t += 1, new e({
										mlink: o.toString(),
										button: n,
										autoLaunchApp: r,
										params: a
									}))
								})
							}, 500)
						})
					}
				}]), e
			}();
		W.promise = u.
	default, W.Promise = A, W.Cookie = C, W.os = x, W.Uri = P, W.browser = U, W.debug = I, W.device = E, W.Render = L, W.env = {
			suHost: e.suHost,
			ulHost: e.ulHost,
			env: e.env,
			model: "shorturl",
			dpEvent: e.dpEvent
		}, W.scanLink(), window.Mlink = W, t.
	default = W
	}).call(t, n(6))
}, function(e, t, n) {
	"use strict";

	function o(e) {
		return e && e.__esModule ? e : {
		default:
			e
		}
	}
	function i(e, t) {
		if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
	}
	Object.defineProperty(t, "__esModule", {
		value: !0
	});
	var r = function() {
			function e(e, t) {
				for (var n = 0; n < t.length; n++) {
					var o = t[n];
					o.enumerable = o.enumerable || !1, o.configurable = !0, "value" in o && (o.writable = !0), Object.defineProperty(e, o.key, o)
				}
			}
			return function(t, n, o) {
				return n && e(t.prototype, n), o && e(t, o), t
			}
		}(),
		a = n(1),
		s = (o(a), n(2)),
		u = o(s),
		c = n(3),
		l = (o(c), n(4)),
		d = (o(l), n(5)),
		h = o(d),
		f = function() {
			function e() {
				var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {};
				i(this, e), this.mlink = t
			}
			return r(e, [{
				key: "init",
				value: function(e) {
					this.data = e || {}, this.getCustomStyles()
				}
			}, {
				key: "getCustomStyles",
				value: function() {
					if (this.customStyles) return this.customStyles;
					var e = {},
						t = "custom_mp_data",
						n = void 0;
					if (this.data.custom_mp_data && "{}" !== this.data.custom_mp_data ? n = this.data.custom_mp_data : (n = this.data.custom_data, t = ""), "string" == typeof n) try {
						n = JSON.parse(n)
					} catch (e) {
						n = {}
					}
					t || (n.ios_json = {
						bgUrl: n.iosBgUrl
					}, n.android_json = {
						bgUrl: n.androidBgUrl
					}, n.open_app_json = {
						bgUrl: n.link && n.link.bgUrl,
						btnText: n.link && n.link.openText,
						btnTextColor: n.link && n.link.textColor,
						btnColor: n.link && n.link.btnColor,
						btnDistanceBottom: "20%"
					}, n.safari_down_json = {
						bgUrl: n.link && n.link.bgUrl,
						btnTextColor: n.link && n.link.textColor,
						btnColor: n.link && n.link.btnColor,
						btnDistanceBottom: "20%"
					}, n.app_back_json = {
						bgUrl: n.link && n.link.bgUrl
					}), e.iosTipsImg = n.ios_json && n.ios_json.bgUrl || "", e.androidTipsImg = n.android_json && n.android_json.bgUrl || "", e.open_app_json = n.open_app_json || {}, e.safari_down_json = n.safari_down_json || {}, e.app_back_json = n.app_back_json || {}, e.count_down_json = n.count_down_json || {};
					for (var o in e)"iosTipsImg" !== o && "androidTipsImg" !== o && (e[o].type = t);
					return e.open_app_json.btnDistanceBottom && (/^[0-9]{1,2}\%$/.test(n.open_app_json.btnDistanceBottom) || (e.open_app_json.btnDistanceBottom = "20%")), this.customStyles = e, e
				}
			}, {
				key: "on",
				value: function(e, t, n) {
					if (e && t && n) {
						var o = t && t.constructor === Array ? t : [t];
						o.forEach(function(t) {
							e.addEventListener(t, n)
						})
					}
				}
			}, {
				key: "off",
				value: function(e, t, n) {
					if (e && t && n) {
						var o = t && t.constructor === Array ? t : [t];
						o.forEach(function(t) {
							e.removeEventListener(t, n)
						})
					}
				}
			}, {
				key: "hideAllPage",
				value: function() {
					var t = this;
					return new h.
				default.Promise(function(n) {
						var o = document.querySelectorAll(".mw-page");
						e.each(o, function(e) {
							var o = void 0,
								i = ["WebkitAnimationEnd", "MozAnimationEnd", "OAnimationEnd", "MsAnimationEnd", "animationEnd", "animationend"],
								r = function r() {
									t.off(e, i, r), clearTimeout(o), n()
								};
							t.on(e, i, r), o = setTimeout(r, 320), e.classList.remove("show"), e.classList.add("hide")
						})
					})
				}
			}, {
				key: "showPage",
				value: function(e) {
					var t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {},
						n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {},
						o = document.querySelector(".mw-page" + e);
					if ("custom_mp_data" === t.type && t.bg) for (var i = o.querySelectorAll(".middle"), r = 0; r < i.length; r++) {
						var a = i[r].getAttribute("style");
						i[r].setAttribute("style", a + ";display:none !important")
					}
					return this.renderPage(o, t, n), this.hideAllPage().then(function() {
						o && (o.classList.add("show"), o.classList.remove("hide"))
					}), o
				}
			}, {
				key: "renderPage",
				value: function(e) {
					var t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {};
					arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {};
					e && (t.bg && this.setBg(e, t.bg), t.title && this.setTitle(e, t.title), t.text && this.setText(e, t.text), t.tipsImg && this.setTipsImg(e, t.tipsImg), t.appIcon && this.setAppIcon(e, t.appIcon), t.appName && this.setAppName(e, t.appName), t.btn && this.setBtn(e, t.btn || {}))
				}
			}, {
				key: "showDownloadTips",
				value: function(e) {
					if (e.match(/:\/\/itunes\.apple\.com/) || e.match(/\.apk($|\?)/)) {
						var t = this.data,
							n = t.productName || t.appName,
							o = t.iconUrl || t.appIcon;
						o += "?imageMogr/v2/thumbnail/300x300";
						var i = this.getCustomStyles().open_app_json || {},
							r = '\n                <h3>正在等您安装</h3>\n                <div class="app-icon"><img id="appIcon" src="' + o + '" alt="" onload="this.style.display=\'block\';" onerror="this.style.display=\'none\';"/></div>\n                <p><b onclick="window.location.reload();">' + n + '</b></p>\n\n                <br/>\n                <a class="mw-button no-margin" onclick="window.location.reload();">我已完成安装</a>\n                <div>如果您刚才不小心取消了安装, 请点击: </div>\n                <p>   \n</p>\n                <a class="mw-button no-margin min outline" href="' + e + '">再次安装</a>';
						this.showPage(".mw-alert", {
							text: r,
							btn: {
								color: i.btnTextColor || "",
								backgroundColor: i.btnColor || ""
							},
							bg: i.bgUrl
						})
					}
				}
			}, {
				key: "showSchemeNotSupport",
				value: function(e) {
					var t = this.getCustomStyles();
					this.showPage(".scheme-notsupported", {
						appName: this.data.productName,
						btn: {
							innerHTML: t && t.safari_down_json && t.safari_down_json.btnText,
							url: e
						}
					})
				}
			}, {
				key: "showContentPage",
				value: function(e) {
					var t = e.productName || e.appName,
						n = e.iconUrl || e.appIcon,
						o = e.mwContent,
						i = this.showPage(".custom-content", {
							appName: t,
							appIcon: n
						}),
						r = i.querySelector("h1"),
						a = i.querySelector(".mw-cc-banner"),
						s = a.querySelector("img"),
						u = i.querySelector(".content"),
						c = o.content || o.summary;
					return r.innerHTML = o.title, a.style.backgroundImage = 'url("' + o.contentImageUri + '")', s.src = o.contentImageUri, u.innerHTML = c, o.contentImageUri || (a.style.display = "none"), i
				}
			}, {
				key: "showAppOpened",
				value: function(e) {
					if (this.mlink.customDataView) return void this.showContentPage(e);
					var t = this.data.productName,
						n = this.data.iconUrl;
					n += "?imageMogr/v2/thumbnail/300x300";
					var o = this.mlink,
						i = this.getCustomStyles().app_back_json || {},
						r = "" + (i.isTextOneShow ? "<h1 " + (i.textOneColor ? 'style="color:' + i.textOneColor + ';"' : "") + ">已经为您打开了App</h1>" : "") + (i.type ? "" : '<div class="app-icon logo">\n                        <img src="' + n + '" onload="onImgLoaded(this)" onerror="onImgError(this)" />\n                    </div>\n                    <div class="blue" onclick="location.reload();" style="font-weight: bold;">' + t + "</div>") + ('<p style="position: absolute;width: 100%;left:0;' + (i.btnDistanceBottom ? "bottom:" + i.btnDistanceBottom : "bottom:20%;") + '">\n                        <a class="linkBtn mw-button" href="javascript:void(0)" style="\n                            ' + (i.btnTextColor ? "color:" + i.btnTextColor + ";" : "") + "\n                            " + (i.btnColor ? "background-color:" + i.btnColor + ";" : "") + '\n                            font-size:1.4em;\n                        \n                        ">' + (i.btnText || "再次打开") + "</a>.\n                    </p>");
					this.showPage(".mw-alert", {
						text: r,
						btn: {
							initAction: function(e) {
								var t = o.applink,
									n = t.match(/^https:\/\//) ? 1 : 0;
								o.showRedirectButton(t, n, e)
							}
						},
						bg: i.bgUrl
					})
				}
			}, {
				key: "showDownloadDialod",
				value: function() {
					var e = this.data,
						t = document.createElement("div"),
						n = '.mw-dialog.mw-only-css {\n                      position: fixed;\n                      left: 0;\n                      top: 0;\n                      right: 0;\n                      bottom: 0;\n                      height: 100%;\n                      width: 100%;\n                      background-color: rgba(0, 0, 0, 0.6);\n                      box-sizing: border-box;\n                      z-index: 99999999999;\n                      display: -ms-flex;\n                      display: flex;\n                      display: -webkit-flex;\n                      display: -webkit-box;\n                      display: -ms-flexbox;\n                      display: flex;\n                      -ms-flex-align: center;\n                      -webkit-align-items: center;\n                      -webkit-box-align: center;\n                      align-items: center;\n                      -webkit-justify-content: space-between;\n                      -ms-flex-pack: justify;\n                      justify-content: space-between;\n                    }\n                    .mw-dialog.mw-only-css > div {\n                      display: block;\n                      width: 100%;\n                    }\n                    .mw-dialog.mw-only-css .mw-close {\n                      width: 80px;\n                      height: 80px;\n                      -webkit-border-radius: 50%;\n                      border-radius: 50%;\n                      display: block;\n                      margin: 2em auto 0;\n                      background: rgba(0, 0, 0, 0.4);\n                      padding: 1.1em;\n                      -webkit-box-sizing: border-box;\n                      -moz-box-sizing: border-box;\n                      box-sizing: border-box;\n                      border: 2px solid #ffffff;\n                    }\n                    .mw-dialog.mw-only-css .mw-close:before,\n                    .mw-dialog.mw-only-css .mw-close:after {\n                      display: block;\n                      content: " ";\n                      height: 0;\n                      width: 100%;\n                      border-bottom: 2px solid rgba(255, 255, 255, 0.8);\n                      transform: rotate(45deg);\n                      position: relative;\n                      top: 50%;\n                    }\n                    .mw-dialog.mw-only-css .mw-close:after {\n                      transform: rotate(-45deg);\n                    }\n                    .mw-dialog.mw-only-css .mw-dialog-container {\n                      max-width: 640px;\n                      overflow: auto;\n                      max-height: 100%;\n                      width: 80%;\n                      padding: 2em 1em;\n                      box-sizing: border-box;\n                      font-size: 14px;\n                      text-align: center;\n                      border: 2px solid #fff;\n                      border-radius: 1em !important;\n                      background-color: #fff;\n                      color: #666;\n                      margin: auto;\n                      box-shadow: 2px 2px 3em rgba(0, 0, 0, 0.2);\n                    }\n                    .mw-dialog.mw-only-css .mw-dialog-container .mw-app-icon {\n                      width: 6em;\n                      height: 6em;\n                    }\n                    .mw-dialog.mw-only-css .mw-dialog-container .mw-app-name {\n                      font-size: 1em;\n                      margin-bottom: 2em;\n                      color: #000;\n                    }\n                    .mw-dialog.mw-only-css .mw-dialog-container .mw-button {\n                      font-size: 1.5em;\n                      padding: 1em 2em;\n                    }\n                    ';
					this.dialodStyleAppend || (this.appenStyle(n), this.dialodStyleAppend = !0);
					var o = e.iconUrl;
					o += "?imageMogr/v2/thumbnail/300x300";
					var i = e.productName,
						r = u.
					default.ios ? e.iosDownloadUrl:
						e.androidDownloadUrl,
						a = '<div class="mw-dialog mw-download-dialog mw-only-css">\n            <div>\n                <div class="mw-dialog-container">\n                    <img class="mw-app-icon" src="' + o + '" onload="this.style.display=\'inline-block\'" onerror="this.style.display=\'none\'" />\n                    <h3 class="mw-app-name" onclick="location.reload();" style="font-weight: bold;">' + i + '</h3>\n                    <div>如果您没有安装,请点击立即安装</div>\n                    <p>\n                        <a class="linkBtn mw-button" href="' + r + '">立即安装</a>\n                    </p>\n                </div>\n                <a href="javascript:void(0);" class="mw-close" onclick="this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);"></a>\n            </div>\n        </div>';
					t.innerHTML = a, a = t.getElementsByTagName("div")[0];
					var s = a.querySelector(".mw-close"),
						c = function() {
							a && (document.body.removeChild(a), a = null)
						};
					s.addEventListener("click", c), s.addEventListener("touchend", c), document.body.appendChild(a)
				}
			}, {
				key: "setBg",
				value: function(e, t) {
					t ? (e.style.backgroundImage = 'url("' + t + '")', e.classList.remove("default")) : (e.style.backgroundImage = "", e.classList.add("default"))
				}
			}, {
				key: "setTipsImg",
				value: function(e, t) {
					var n = e.querySelector(".tips");
					t ? (n.style.backgroundImage = 'url("' + t + '")', e.classList.remove("default")) : (n.style.backgroundImage = "", e.classList.add("default"))
				}
			}, {
				key: "setTitle",
				value: function(e, t) {
					var n = e.querySelector("h3");
					n && (n.innerHTML = t)
				}
			}, {
				key: "setText",
				value: function(e, t) {
					var n = e.querySelector(".tips");
					n && (n.innerHTML = t)
				}
			}, {
				key: "setAppName",
				value: function(t, n) {
					e.each(t.querySelectorAll(".app-name"), function(e) {
						e.innerHTML = n
					})
				}
			}, {
				key: "setAppIcon",
				value: function(t, n) {
					n && (n += "?imageMogr/v2/thumbnail/300x300", e.each(t.querySelectorAll(".app-icon img"), function(e) {
						e.parentElement.style.backgroundImage = "none", e.setAttribute("src", n)
					}))
				}
			}, {
				key: "setBtn",
				value: function(t) {
					var n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {},
						o = n;
					e.each(t.querySelectorAll(".mw-button"), function(e) {
						o.backgroundColor && (e.style.backgroundColor = o.backgroundColor), o.color && (e.style.color = o.color), o.btnDistanceBottom && (e.parentElement.style.bottom = o.btnDistanceBottom), n.innerHTML && (e.innerHTML = n.innerHTML), n.initAction ? n.initAction(e) : (n.url && (e.href = n.url), n.onclick && (e.onclick = n.onclick))
					})
				}
			}, {
				key: "hidePage",
				value: function(e) {
					var t = document.querySelector(e);
					t && (t.classList.remove("show"), t.classList.add("hide"))
				}
			}, {
				key: "appenStyle",
				value: function(e) {
					var t = document.createElement("style");
					t.textContent = e, document.getElementsByTagName("head")[0].appendChild(t)
				}
			}], [{
				key: "each",
				value: function(e, t) {
					for (var n = 0; n < e.length; n++) t(e[n], n)
				}
			}]), e
		}();
	t.
default = f
}, function(e, t) {
	"use strict";
	Object.defineProperty(t, "__esModule", {
		value: !0
	}), t.
default = {
		sha1: function(e) {
			var t, n, o, i, r, a, s, u, c, l, d = function(e, t) {
					return e << t | e >>> 32 - t
				},
				h = function(e) {
					var t, n, o = "";
					for (t = 7; t >= 0; t--) n = e >>> 4 * t & 15, o += n.toString(16);
					return o
				},
				f = [],
				p = 1732584193,
				m = 4023233417,
				v = 2562383102,
				g = 271733878,
				w = 3285377520,
				y = [];
			for (e = this.utf8_encode(e), l = e.length, n = 0; n < l - 3; n += 4) o = e.charCodeAt(n) << 24 | e.charCodeAt(n + 1) << 16 | e.charCodeAt(n + 2) << 8 | e.charCodeAt(n + 3), y.push(o);
			switch (3 & l) {
			case 0:
				n = 2147483648;
				break;
			case 1:
				n = e.charCodeAt(l - 1) << 24 | 8388608;
				break;
			case 2:
				n = e.charCodeAt(l - 2) << 24 | e.charCodeAt(l - 1) << 16 | 32768;
				break;
			case 3:
				n = e.charCodeAt(l - 3) << 24 | e.charCodeAt(l - 2) << 16 | e.charCodeAt(l - 1) << 8 | 128
			}
			for (y.push(n); 14 !== (15 & y.length);) y.push(0);
			for (y.push(l >>> 29), y.push(l << 3 & 4294967295), t = 0; t < y.length; t += 16) {
				for (n = 0; n < 16; n++) f[n] = y[t + n];
				for (n = 16; n <= 79; n++) f[n] = d(f[n - 3] ^ f[n - 8] ^ f[n - 14] ^ f[n - 16], 1);
				for (i = p, r = m, a = v, s = g, u = w, n = 0; n <= 19; n++) c = d(i, 5) + (r & a | ~r & s) + u + f[n] + 1518500249 & 4294967295, u = s, s = a, a = d(r, 30), r = i, i = c;
				for (n = 20; n <= 39; n++) c = d(i, 5) + (r ^ a ^ s) + u + f[n] + 1859775393 & 4294967295, u = s, s = a, a = d(r, 30), r = i, i = c;
				for (n = 40; n <= 59; n++) c = d(i, 5) + (r & a | r & s | a & s) + u + f[n] + 2400959708 & 4294967295, u = s, s = a, a = d(r, 30), r = i, i = c;
				for (n = 60; n <= 79; n++) c = d(i, 5) + (r ^ a ^ s) + u + f[n] + 3395469782 & 4294967295, u = s, s = a, a = d(r, 30), r = i, i = c;
				p = p + i & 4294967295, m = m + r & 4294967295, v = v + a & 4294967295, g = g + s & 4294967295, w = w + u & 4294967295
			}
			return c = h(p) + h(m) + h(v) + h(g) + h(w), c.toLowerCase()
		},
		utf8_encode: function(e) {
			return encodeURIComponent(e)
		}
	}
}, function(e, t) {}]);