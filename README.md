


# Simplifying Android Development with Coroutines and Flows

<a href="https://www.packtpub.com/product/simplifying-android-development-with-coroutines-and-flows/9781801816243"><img src="https://static.packt-cdn.com/products/9781801816243/cover/smaller" alt="Simplifying Android Development with Coroutines and Flows" height="256px" align="right"></a>

This is the code repository for [Simplifying Android Development with Coroutines and Flows](https://www.packtpub.com/product/simplifying-android-development-with-coroutines-and-flows/9781801816243), published by Packt.

**Learn how to use Kotlin coroutines and the flow API to handle data streams asynchronously in your Android app**

## What is this book about?
Coroutines and flows are the new recommended way for developers to carry out asynchronous programming in Android using simple, modern, and testable code. This book will teach you how coroutines and flows work and how to use them in building Android applications, along with helping you to develop modern Android applications with asynchronous programming using real data.

This book covers the following exciting features:
* Understand how coroutines and flows differ from existing ways
* Apply asynchronous programming in Android with coroutines and flows
* Find out how to build your own coroutines and flows in Android
* Handle, manipulate, and combine data in coroutines and flows
* Handle cancellations and exceptions from coroutines and flows
* Discover how to add tests for your coroutines and flows
* Integrate coroutines and flows into your Android projects

If you feel this book is for you, get your [copy](https://www.amazon.com/Simplifying-Android-Development-Coroutines-Flows-dp-1801816247/dp/1801816247/ref=mt_other?_encoding=UTF8&me=&qid=) today!


## Instructions and Navigations
All of the code is organized into folders. For example, Chapter02.

The code will look like the following:
```
CoroutineScope(Dispatchers.IO).launch {
    val movies = movieService.getMovies()
    withContext(Dispatchers.Main) {
      displayMovies(movies)
    }
}
```

**Following is what you need for this book:**
This book is for intermediate-level Android developers who want to level up their Android app-building skills. Familiarity with Android development and basic knowledge of Kotlin are needed to make the most of this book.

With the following software and hardware list you can run all code files present in the book (Chapter 1-7).

### Software and Hardware List
| Chapter | Software/Hardware required | OS required |
| -------- | ------------------------------------ | ----------------------------------- |
| 1-7 | Android Studio | Windows Mac OS X and Linux  |


### Related products
* Clean Android Architecture [[Packt]](https://www.packtpub.com/product/clean-android-architecture/9781803234588) [[Amazon]](https://www.amazon.com/Clean-Android-Architecture-decoupled-applications-dp-180323458X/dp/180323458X/ref=mt_other?_encoding=UTF8&me=&qid=)

* Kickstart Modern Android Development with Jetpack and Kotlin [[Packt]](https://www.packtpub.com/product/kickstart-modern-android-development-with-jetpack-and-kotlin/9781801811071) [[Amazon]](https://www.amazon.com/Kickstart-Modern-Android-Development-Jetpack/dp/1801811075)


## Get to Know the Author

**Jomar Tigcal**
is an Android developer with over 10 years of experience in mobile and software development. He worked on various stages of app development for small startups to large companies. Jomar has also given talks and conducted training and workshops on Android. In his free time, he likes running and reading. He lives in Vancouver, Canada with his wife Celine.


## Other books by the author
* [How to Build Android Apps with Kotlin](https://www.packtpub.com/product/how-to-build-android-apps-with-kotlin/9781838984113?_ga=2.143249015.356192373.1658578087-861421203.1658578087)
### Download a free PDF

 <i>If you have already purchased a print or Kindle version of this book, you can get a DRM-free PDF version at no cost.<br>Simply click on the link to claim your free PDF.</i>
<p align="center"> <a href="https://packt.link/free-ebook/9781801816243">https://packt.link/free-ebook/9781801816243 </a> </p>