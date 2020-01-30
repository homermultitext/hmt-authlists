## Guide to markup of dingbat characters

Encode dingbat characters in your TEI XML editions by doing two things:

1. In the `teiHeader` of your document, add the `encodingDesc` defining glyphs for dingbats.  You can copy this from the `[dingbats-template.xml](dingbats-template.xml)` file in this directory.
2. In the text of your edition, use the TEI `g` element with a `@ref` attribute giving the XML ID for your glyph.


The following table lists all currently defined dingbat glyphs with an illustration from the Venetus  B or Burney 86 manuscript, and an example of XML usage with the ID defined in the `encodingDesc` of your document.



| Dingbat                              | Example                                                                                                                                                                               | XML glyph example                |
|:-------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------------------------------|
| The "ice-cream cone" dingbat         | ![](http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/hmt/vbbifolio/v1/vb_128v_129r.tif&RGN=0.7054,0.5849,0.009948,0.0170&WID=100&CVT=JPEG)      | `<g ref="#ice-cream-cone"/>`     |
| The "curly pipe" dingbat             | ![](http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/hmt/burney86/v1/burney_ms_86_f083r.tif&RGN=0.4095,0.6407,0.05600,0.02411&WID=100&CVT=JPEG) | `<g ref="#curly-pipe"/>`         |
| The "reverse curly pipe" dingbat     | ![](http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/hmt/burney86/v1/burney_ms_86_f083r.tif&RGN=0.2916,0.6597,0.03705,0.02224&WID=100&CVT=JPEG) | `<g ref="#reverse-curly-pipe/>"` |
| The "b" dingbat                      | ![](http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/hmt/burney86/v1/burney_ms_86_f083r.tif&RGN=0.4072,0.6629,0.02526,0.02496&WID=100&CVT=JPEG) | `<g ref="#lower-case-b"/>`       |
| The "asterisk" dingbat               | ![](http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/hmt/burney86/v1/burney_ms_86_f083r.tif&RGN=0.1964,0.6853,0.02926,0.02004&WID=100&CVT=JPEG) | `<g ref="#asterisk/>"`           |
| The "sideways 2-rung ladder" dingbat | ![](http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/hmt/burney86/v1/burney_ms_86_f083r.tif&RGN=0.1851,0.7018,0.04674,0.02734&WID=100&CVT=JPEG) | `<g ref="#sideways-ladder/>"`    |
| The "Upside-Down Capital J" dingbat  | ![](http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/hmt/burney86/v1/burney_ms_86_f083r.tif&RGN=0.3587,0.7309,0.03263,0.02343&WID=100&CVT=JPEG) | `<g ref="#upside-down-J/>"`      |
| The "Three-Runged Sigma" dingbat     | ![](http://www.homermultitext.org/iipsrv?OBJ=IIP,1.0&FIF=/project/homer/pyramidal/deepzoom/hmt/burney86/v1/burney_ms_86_f083r.tif&RGN=0.3442,0.7516,0.04905,0.02496&WID=100&CVT=JPEG) | `<g ref="#three-runged-sigma"/>` |
