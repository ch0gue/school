import sys
from timeit import default_timer as timer


def brute_force(pat, text):
    count = 0
    m = len(pat)
    n = len(text)
    for i in range(n-m):
        j = 0
        while j < m and text[i+j] == pat[j]:
            j += 1
        if j == m:
            count += 1
    return count


def boyer_moore(pat, text):
    alphabet = set(text)
    last_occurrence_dict = dict()
    for letter in alphabet:
        last_occurrence_dict[letter] = pat.rfind(letter)

    n = len(text)
    m = len(pat)
    i = m - 1
    j = m - 1
    count = 0
    while i < n:
        if text[i] == pat[j]:
            if j == 0:
                count += 1
                last = last_occurrence_dict[text[i]]
                i = i + m - min(j, 1 + last)
                j = m - 1
            else:
                j -= 1
                i -= 1
        else:
            last = last_occurrence_dict[text[i]]
            i = i + m - min(j, 1 + last)
            j = m - 1
    return count


def suffix_array(pat, text, sa):
    n = len(text)
    m = len(pat)
    left = 0
    right = n - 1
    count = 0
    while left <= right:
        mid = left + (right - left)/2
        suffix = text[int(sa[mid]):int(sa[mid])+m]
        if pat == suffix:
            index = mid
            while pat == suffix:
                index -= 1
                suffix = text[int(sa[index]):int(sa[index]) + m]
            index += 1
            suffix = text[int(sa[index]):int(sa[index]) + m]
            while pat == suffix:
                index += 1
                count += 1
                suffix = text[int(sa[index]):int(sa[index]) + m]
            break
        elif pat < suffix:
            right = mid - 1
        else:
            left = mid + 1
    return count


def main():

    txt_file = sys.argv[1]
    sa_file = sys.argv[2]
    pat_file = sys.argv[3]

    text = open(txt_file, 'r').read()
    pattern = open(pat_file, 'r').readlines()
    sa = open(sa_file, 'r').readlines()

    for s in sa:
        s.strip("\n")

    for p in pattern:
        pa = p.strip("\n")
        print("\ntext:\t\"" + txt_file + "\"")
        print("pattern:\t\"" + pa + "\"")
        start = timer()
        suffix_array(p, text, sa)
        end = timer()
        print("occurrences:\t" + str(suffix_array(pa, text, sa)))
        print("Suffix Array:\t" + str(end - start) + " s")
        start = timer()
        brute_force(p, text)
        end = timer()
        print("Brute Force:\t" + str(end - start) + " s")
        start = timer()
        boyer_moore(p, text)
        end = timer()
        print("Boyer-Moore:\t" + str(end - start) + " s")


if __name__ == "__main__":
    main()
