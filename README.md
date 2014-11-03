*******************
jarファイルの作り方
*******************

今日ご紹介するのは
ＪＡＶＡアプリケーションのコンパイルとjarファイルの作り方です。

ソースの中身はまた後日解説するとしてとりあえず早速コンパイル
してみましょう。

下記のファイルを自分のＰＣの任意の場所に格納してください。

コマンドプロンプトから
ファイルを格納した場所まで移動して

-------------------------------------------------------
>javac Anclock.java
-------------------------------------------------------
でソースをコンパイルします。

これによりAnclock.classとAnclock$1.classができます。

この状態でコマンドプロンプトから
-------------------------------------------------------
>java Anclock
-------------------------------------------------------
と入力すると
プログラムが実行されます。

自分で作成したプログラムを他の人にみて貰いたい場合、

『まず、このクラスとこのクラスを同じ場所において、
イメージフォルダを同じところに置いて、
コマンドプロンプトからjava XXXXと入力して・・・』
とか言っても誰も面倒くさがってやってくれません。

そこで、jarファイルの登場となります！

jarファイルは簡単に言えばLZHやZIPのようなもので
複数のクラスやイメージをひとつのファイルにまとめてくれるもので
さらに、ダブルクリックするだけで
プログラムを実行できるようにしてくれます。

まあ説明はともかく、早速作ってみましょう。
まず、『マニフェストファイル』というものが必要になります。
最近、選挙前になるとよく政党が発表するアレですね。

Anclock.mfファイル
このファイルでこのアプリケーション
のMainクラスを指定してあげます。

----------------------
Main-Class: Anclock
----------------------

このマニフェストファイルをクラスファイルと同じところに
配置したら、あとはコマンドプロンプトから
以下のように入力するだけです。

-------------------------------------------------------
>jar cvfm Anclock.jar Anclock.mf *.class *.jpg
-------------------------------------------------------

さあ、jarファイルができましたか？

ではこのjarファイルをダブルクリックしてみてください。
プログラムが実行できましたね！

是非お試しください！
