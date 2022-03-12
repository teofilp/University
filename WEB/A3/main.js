(() => {
  var count = 1;

  setInterval(() => {
    document.title = `(${count}) YouTube`;
    count++;
  }, 10000);
})();

var videos = [
  {
    name: "Watch Sky News live",
    channel: "Sky News",
    videoImageUrl:
      "https://i.ytimg.com/vi/9Auq9mYxFEE/hq720_live.jpg?sqp=CLzaspEG-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAIislc30fRiqfV126f-NqQK_D--w",
    channelImageUrl:
      "https://yt3.ggpht.com/E96qzkAoX81DQs7wqRHR4rNk1esa4quBPzda2QRzImlhoHOVgRdAN8o-S0Rb_hpygo_n4LdhwTE=s68-c-k-c0x00ffffff-no-rj",
    views: "101K views",
    posted: "2 weeks ago",
    duration: "12:24",
    isLive: true,
  },
  {
    name: "Ferrari 812 v BMW M5 CS: DRAG RACE",
    channel: "carwow",
    videoImageUrl:
      "https://i.ytimg.com/vi/24YYHOfAuJY/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCsAoVGt5bpTomCFG0Id6Oel6PQ8A",
    channelImageUrl:
      "https://yt3.ggpht.com/ytc/AKedOLQuONFgFu1BtXgGqGhmOmc8c8JqTVDADFLTOKyt4g=s68-c-k-c0x00ffffff-no-rj",
    views: "1.6M views",
    posted: "2 weeks ago",
    duration: "8:46",
  },
  {
    name: "Biden Means Pain At The Pump | Ep. 1448",
    channel: "Ben Shapiro",
    videoImageUrl:
      "https://i.ytimg.com/vi/84QN5zDCioE/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAz_-Vnoe2uyClCuD9dZpK_5XmlIg",
    channelImageUrl:
      "https://yt3.ggpht.com/ytc/AKedOLRUEE4xRVlkdSJpCy-I9VjJuUX03LPzpXiHjI8bZA=s68-c-k-c0x00ffffff-no-rj",
    views: "233K views",
    posted: "4 days ago",
    duration: "49:22",
  },
  {
    name: "Managing your .NET app configuration like a pro",
    channel: "Nick Chapsas",
    videoImageUrl:
      "https://i.ytimg.com/vi/J0EVd5HbtUY/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLBZFjFmG4wj_hVzRfSQ4Dl_hr6z6g",
    channelImageUrl:
      "https://yt3.ggpht.com/ytc/AKedOLQcru_UEZ-DP0EX0wDMD3TR-r59xvVvphPHrvVv_A=s68-c-k-c0x00ffffff-no-rj",
    views: "32K views",
    posted: "3 weeks ago",
    duration: "15:16",
  },
  {
    name: 'MacBook Pro 14" M1 Pro Setup for programming & productivit...',
    channel: "codeshifu",
    videoImageUrl:
      "https://i.ytimg.com/vi/CIEYMRWvi3s/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCz5mbawLwoOSiQTKOjtwJSx3ruEw",
    channelImageUrl:
      "https://yt3.ggpht.com/mSQay_QlEwXcbwrrpJi2o_JV-ddrmrkVRN8EGVgkb442QBJwdPdG1YLzEtw0TYmVGgzJ1u0=s68-c-k-c0x00ffffff-no-rj",
    views: "5.7K views",
    posted: "1 month ago",
    duration: "11:51",
  },
  {
    name: "MY TWIN TURBO FERRARI F12 IS BACK WITH MAJOR UPGRADES …",
    channel: "DailyDrivenExotics",
    videoImageUrl:
      "https://i.ytimg.com/vi/DAlqI6Zy-2U/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAItFemBI7oGk9P9BPXnX7bEOqXQA",
    channelImageUrl:
      "https://yt3.ggpht.com/ytc/AKedOLSfStez6TCc55MnpQKokHT2Du2Q3GqGzmbt49M4=s68-c-k-c0x00ffffff-no-rj",
    views: "159K views",
    posted: "5 hours ago",
    duration: "26:29",
  },
  {
    name: "Who Has Deadlier Missiles? Russia or United States",
    channel: "The Infographics Show",
    videoImageUrl:
      "https://i.ytimg.com/vi/XmCunk8RiMQ/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLA4OcS1189A90fFPIFSWoKcdp1hwQ",
    channelImageUrl:
      "https://yt3.ggpht.com/ytc/AKedOLRfkc-17P5sWF3G4IH_PCzh-1rM3Z8uoyhGJczVvg=s68-c-k-c0x00ffffff-no-rj",
    views: "1.4M views",
    posted: "1 year ago",
    duration: "14:10",
  },
  {
    name: "I installed Windows on Steam Deck and I regret it",
    channel: "Linus Tech Tips",
    videoImageUrl:
      "https://i.ytimg.com/vi/uNt_ReLwk40/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLC9JvMvUM1bikOclmfSqtK61p1YcQ",
    channelImageUrl:
      "https://yt3.ggpht.com/ytc/AKedOLTw_T5rUQdrd1Xw9g6Gj_OHcL4xbFiE98gJHV3H2Q=s68-c-k-c0x00ffffff-no-rj",
    views: "184K views",
    posted: "2 hours ago",
    duration: "18:22",
  },
  {
    name: "Consultații Mate-Info UBB | 2021-2022 | Algoritmi care lucreaza cu tipuri definite de utilizator",
    channel: "Dan Mircea Suciu",
    videoImageUrl:
      "https://i.ytimg.com/vi/7eSBQtH3ge4/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLBf6sDb-X8E8g2aKAa5k26Gvu6MZg",
    channelImageUrl:
      "https://yt3.ggpht.com/ytc/AKedOLQZI9SpezjIGTiiEH3F_Wl7gKVledKUrjH-M4Aaqw=s68-c-k-c0x00ffffff-no-rj",
    views: "86 views",
    posted: "Streamed 7 hours ago",
    duration: "1:25:46",
  },
  {
    name: "How I would learn to code (if I could start over)",
    channel: "Nick White",
    videoImageUrl:
      "https://i.ytimg.com/vi/Rl-SWiuZ24o/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCh3-NuAnbJHWt_a9rGSNI5Tq9X3w",
    channelImageUrl:
      "https://yt3.ggpht.com/0B67SieAFdmemnoCm-AlJ80ZDGgIUA2dBo1KwwzFfXONnXT0oFPNKD11fT72LMXiqeWdKDSAVw=s68-c-k-c0x00ffffff-no-rj",
    views: "628K views",
    posted: "4 months ago",
    duration: "12:08",
  },
  {
    name: "Bad Wolves - Lifeline (Official Music Video)",
    channel: "Better Noise Music",
    videoImageUrl:
      "https://i.ytimg.com/vi/WBYNG4NW6m4/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAMxKt3gdR9JhXuEqe-AVjT17f1Rw",
    channelImageUrl:
      "https://yt3.ggpht.com/ytc/AKedOLT6PY7cUbMDD4i6n-YmhrxiFp2kczca-hMGWV8YeQ=s68-c-k-c0x00ffffff-no-rj",
    views: "2.3M views",
    posted: "6 months ago",
    duration: "3:26",
  },
  {
    name: "The Chinese Student Crisis",
    channel: "PolyMatter",
    videoImageUrl:
      "https://i.ytimg.com/vi/cQWlnTyOSig/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCRVWu_cA5h0UixbPjmpdtxA5lyfg",
    channelImageUrl:
      "https://yt3.ggpht.com/ytc/AKedOLTLiDcgmlAJ_ZsCmbEF_wMF94DsBDllDcmD-m05Og=s68-c-k-c0x00ffffff-no-rj",
    views: "65K views",
    posted: "4 hours ago",
    duration: "21:44",
  },
];

(() => {
  var item = document.getElementById("page-item");
  var parent = item.parentNode;

  item.remove();

  videos.forEach((video) => {
    const newItem = item.cloneNode(true);

    const videoImage = newItem.querySelector("img.video-image");
    videoImage.src = "https://www.w3schools.com/images/w3lynx_200.png";

    if (video.isLive) {
      newItem.classList.add("is-live");
    }
    newItem.querySelector("h4.video-title").innerHTML = video.name;
    newItem.querySelector("span.channel-name").innerHTML = video.channel;
    newItem.querySelector("span.views-count").innerHTML = video.views;
    newItem.querySelector("span.post-date").innerHTML = video.posted;
    newItem.querySelector("img.channel-image").src = video.channelImageUrl;
    newItem.querySelector("img.video-image").src = video.videoImageUrl;
    newItem.querySelector(".video-duration").innerHTML = video.duration;

    parent.appendChild(newItem);
  });
})();
