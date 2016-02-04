# mrs-clojure

An example project of what a new MixRadio template might look like.

## Rationale

MixRadio template gives us a lot of things for free that other templates out there don't: low-level web server instrumentation, logging and graphite bootstrapping, basic resources needed for internal deployment, build and release configuration. Feature wise it's not bad at all, but times have moved on and there are performance and workflow improvements that can be made.

* Reloadable workflow
In particular, the ability to start and stop the web server and flush application state (caches etc.) without restarting the REPL is a big productivity win.

* Deterministic starting and stopping of resources
There are a number of ways to do this. At time of writing I think it's fair to say that Component is the de facto standard and the conservative choice. The thriving ecosystem of off-the-shelf components is also very attractive.

* Testing
Midje makes hard tests hard to write. It's about time we oust Midje (or at least make it optional) in favour of clojure.test.

* Auto-generated API documentation.
This will require moving away from Compojure. Bidi or Compojure-API are candidates here.

* Better HTTP compliance out of the box + Async request handling
Yada is the most interesting project that would give us this but it's fairly new and hasn't been battle tested like Compojure has. Nevertheless it's worth trialing and observe how it shapes up in practice.

A couple of caveats here: It's built on top of Aleph, an async web server built on top of Netty, meaning all of our request-level instrumentation will need to be rewritten. Most of our ring middleware becomes unsuitable, yet in a lot of cases Yada takes care of most of that so we can do away with most of it.

* Utility libs
I don't think we have enough utility libraries in our current template. libraries like camel-snake-kebab, medley, schema (?) fall into this category. Always better to include more than you're likely to need and remove at will, within reason :)

* Clojurescript refresh
Now we've built quite a few internal tools we can revisit the bundled dependencies and plug-ins needed for streamlines cljs development.
