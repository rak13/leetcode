/*
- iterate from reverse
- push into stack all characters excluding '[' (including ']')
- when we encounter '[' pop from stack until we find ']' on the stack
	-while popping build a string (curStr) from those characters
- as stated '[' will have a number in front of it, get that number, k
- generate this (partially) decoded string by replicating curStr k number of times
	-push it onto stack
- repeat these steps	
	
- finally concatenate all strings in stack
	= fully decoded string

*/


class Solution {
    
    public String buildStr(String str, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<n; i++){
            sb.append(str);
        }
        return sb.toString();
    }
    
    public int getNum(int bg, int ed, String str) {
        int val = 0;
        for(int i = bg; i<=ed; i++) {
            val = val*10 + str.charAt(i) - '0';
        }
        return val;
    } 
    
    public String decodeString(String s) {
        Stack stk =  new Stack();
        
        for(int i = s.length() - 1; i >= 0; i--){
            char curChar = s.charAt(i);
            if(curChar != '[') {
                stk.push(Character.toString(curChar));
            }
            else {
                StringBuilder curStr = new StringBuilder();
                //start popping chars and make string until we find "]" in stack
                while(!stk.empty() && !stk.peek().equals("]")) {
                    curStr.append(stk.peek());
                    stk.pop();
                }
				//pop ']'
                stk.pop();
                i--;
                
                //for 123[abc]
                //we found curStr = "abc"  
                //i is now at 3 - the last digit of number - least significant digit - edNumPos

                int edNumPos = i;
				
                int bgNumPos = i;

                //iterate to the 1st digit of number - "1" - the most significant digit - bgNumPos
                while(i>=0){
                    int curDigit = s.charAt(i) - '0';
                    if(curDigit >=0 && curDigit <= 9) {
                        i--;
                    }
                    else {
                        break;
                    }
                }
                i++;
                bgNumPos = i;
                
                int repeatingValue = getNum(bgNumPos, edNumPos, s);
				
				//push the generated / decoded string 
                stk.push(buildStr(curStr.toString(), repeatingValue));
            }
        }
        StringBuilder result = new StringBuilder();
        while(!stk.empty()) {
            result.append(stk.pop());
        }
        return result.toString();
    }
}
