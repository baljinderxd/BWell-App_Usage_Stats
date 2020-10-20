// 76
class Solution {
    public String minWindow(String s, String t) {
        if(t.length() == 0)
            return "";
        
        int ns = s.length();
        int nt = t.length();
        int occ[] = new int[128];
        int ei = 0, si = 0, head = 0, count = nt, ans = (int)1e8;
        
        for(int i = 0; i < nt; i++)
            occ[ t.charAt(i) ]++;
        
     
        while(ei < ns)
        {
            if(occ[s.charAt(ei++)]-- > 0)
                count--;
            while(count == 0)
            {
                if(ei - si < ans)
                    ans = ei - (head = si);
                if(occ[s.charAt(si++)]++ == 0)
                    count++;
            }
        }
        return ans == (int)1e8 ? "" : s.substring( head, head+ans );
    }
}
