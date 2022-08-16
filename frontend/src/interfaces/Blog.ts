export interface Keyword {
  keyword : string;
}

export interface Bookmark{
  blogName : String; 
  blogUrl : String; 
  portalType : String; 
}

export interface BookmarkDelete{
  memberId : String;
  blogUrl : String;
}